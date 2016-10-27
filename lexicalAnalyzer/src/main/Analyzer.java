package main;

import java.util.ArrayList;

public class Analyzer {
	
	Automata automata;
	
	//��ʼ���Զ�������String��ʽ���Զ�������ת��Ϊ��������ʽ
	public Analyzer(ArrayList<String> translist) {
		ArrayList<Transformation> transformList = new ArrayList<>();
		ArrayList<String> endStatusList = new ArrayList<>();
		
		for(String str : translist){
			String[] temp  = str.split(" ");
			if(temp.length == 3){
				Transformation t = new Transformation(temp[0], temp[1], temp[2]);
				transformList.add(t);
			}
			if(temp[0].equals("final")){
				for(int i = 1 ; i < temp.length ; i ++){
					endStatusList.add(temp[i]);
				}
			}
		}
		automata = new Automata(transformList, endStatusList);
	}
	

	public void deal(ArrayList<String> input) {
		//���ַ�һ�����������Զ���
		String largeStr = "";
		for(String str: input){
			largeStr = largeStr + str + '\n';
			
		}
		for(int i = 0 ; i < largeStr.length() ; i ++){
			boolean success = false;
			try {
				success = automata.toNextStatus(largeStr.charAt(i));
				if(success){
					//����ɹ���Ӧ�û���һ���ַ�������ַ�����һ���ɹ��������ˣ���û�о�������ش�ͷ����
					i --;
					//���ҽ���token���
					buildToken(automata.getLastStatus(),automata.getPresentString());
				}
			} catch (Exception e) {
				int lastLineNum = largeStr.substring(0, i).lastIndexOf('\n') + 1;
				int lineNum = largeStr.substring(0, i).split("\n").length;
				i = largeStr.indexOf('\n', i) + 1;//����һ�п�ʼ����
				System.err.println("\nError! Error occurs in Line " + lineNum
						+ " ,which is\n" + largeStr.substring(lastLineNum, i));
			}
		}
		
	}
	
	private void buildToken(String presentStatus, String presentString) {
		if(presentStatus.equals("ID")){
			presentStatus = Constant.getTokenTypeFromRemainedWord(presentString);
		}else if (presentStatus.equals("SimpleSuccess") || presentStatus.equals("MiddleSuccess")
				|| presentStatus.equals("Middle")){
			presentStatus = Constant.getTokenTypeFromSign(presentString);
		}
		else if (presentStatus.equals("NeqSuccess")){
			presentStatus = "Neq";
		}
		else if (presentStatus.equals("LineComment") || presentStatus.equals("Comment")){
			//We willingly ignore comment
			return;
		}
		System.out.println("( " + presentStatus + " , \"" +  presentString + "\" )");
	}
	
	public ArrayList<Transformation> getTransformList() {
		return automata.getTransformList();
	}

	public ArrayList<String> getEndStatusList() {
		return automata.getEndStatusList();
	}

}
