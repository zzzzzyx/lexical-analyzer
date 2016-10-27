package main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Automata {

	String presentStatus;
	String lastStatus;
	ArrayList<Transformation> transformList;
	StringBuilder presentString;
	char lastChar;//For error handling

	ArrayList<String> endStatusList;
	
	public Automata(ArrayList<Transformation> transformList, ArrayList<String> endStatusList) {
		this.transformList = transformList;
		this.endStatusList = endStatusList;
		this.presentStatus = "Start";
		presentString = new StringBuilder();
	}

	//�Զ�����ÿ��ת���ķ���,������ֵΪ��ʱ��ƥ��ɹ�������ƥ��ʧ�ܣ�ƥ��ʧ��ʱӦ�û���һ���ַ�
	public boolean toNextStatus(char nextChar){
		if(presentStatus.equals("Start")){
			presentString.delete(0, presentString.length());			
		}

		boolean hasComparision = false;
		a:for(Transformation item: transformList){
			if(item.preStatus.equals(presentStatus)){
				if(compare(nextChar, item.edge)){
					hasComparision = true;
				}else{
					continue a;
				}
				presentStatus = item.nextStatus;
				hasComparision = true;
				break a;
			}
		}
		if(!hasComparision){
			if(endStatusList.contains(presentStatus)){
				lastStatus = presentStatus;
				presentStatus = "Start";
				lastChar = nextChar;
				return true;
			}else{
				System.out.println("Error! presentStatus is " + presentStatus + " -- presentChar is " + nextChar);
			}
			presentStatus = "Start";
		}
		presentString.append(nextChar);
		lastChar = nextChar;
		return false;
	}

	private boolean compare(char nextChar, String re) {
		String realRe = re;
		if(Constant.reExplainer.containsKey(re)){
			realRe = Constant.reExplainer.get(re);
		}
		
		Pattern pattern = Pattern.compile(realRe);
		Matcher matcher = pattern.matcher(String.valueOf(nextChar));
		// ����������ʱ��������true�����򷵻�false
		boolean b = matcher.matches();
		
		return b;
	}
	
	public ArrayList<Transformation> getTransformList() {
		return transformList;
	}

	public ArrayList<String> getEndStatusList() {
		return endStatusList;
	}
	
	public String getLastStatus() {
		return lastStatus;
	}

	public String getPresentString() {
		return presentString.toString();
	}
}
