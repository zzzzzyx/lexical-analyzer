package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class Analyzer {
	
	HashMap<Integer, HashMap<Character, String>> parsingTable;
	ArrayList<String> grammar;
	
	Stack<Integer> statusStack;
	Stack<Character> symbolStack;
	Queue<Character> inputQueue;
	boolean notEnd = true;
	
	public Analyzer(ArrayList<String> parsingTable, ArrayList<String> grammar) {
		this.parsingTable = HandleRawData.handleParsingTable(parsingTable);
		this.grammar = grammar;
		statusStack = new Stack<>();
		statusStack.push(0);
		symbolStack = new Stack<>();
		inputQueue = new LinkedBlockingQueue<>();
	}
	
	public void deal(ArrayList<String> input) {
		String firstLine = input.get(0) + '$';
		
		for(char c: firstLine.toCharArray()){
			inputQueue.add(c);
		}
		while(notEnd){
			dealOneChar();
		}
	}
	
	//����һ��һ���ַ���ɵ�������
	private void dealOneChar() {
		char c = inputQueue.peek();
		String nextStep = searchTable(statusStack.peek(), c);
		if(nextStep == null){
			String token = "The input doesnot accord to the grammar!";
			System.out.println(token);
			MyFileManager.writeToken(token);
			notEnd = false;
		}
		else if(nextStep.toLowerCase().charAt(0) == 'r'){
			//��Լ����
			//���Ƚ���Լ�ı��ʽ��ȡ����
			String expr = grammar.get(Integer.parseInt(nextStep.substring(1))).trim();
			
			String[] g = expr.split("=");
			//����ջ��״̬ջ�����Ĵ�����ͬ
			for(int i = 0 ; i < g[1].length() ; i ++){
				symbolStack.pop();
				statusStack.pop();
			}
			
			//ѹ���µķ���,�Ҵ˷��ſ϶��Ƿ��ս��
			symbolStack.push(g[0].charAt(0));
			
			//ѹ���µ�״̬
			statusStack.push(Integer.parseInt(searchTable(statusStack.peek(),symbolStack.peek())));
			
			String token = "��Լ: ���ʽ���: " + Integer.parseInt(nextStep.substring(1)) + " ���ʽ: " + expr;
			System.out.println(token);
			MyFileManager.writeToken(token);
		}else if (nextStep.toLowerCase().charAt(0) == 's'){
			//�������
			//����һ��״̬������s��������֣�����ջ��
			statusStack.push(Integer.parseInt(nextStep.substring(1)));
			symbolStack.push(c);
			String token = "����: �������: " + statusStack.peek() + " ����״̬: " + symbolStack.peek();
			System.out.println(token);
			MyFileManager.writeToken(token);
			inputQueue.poll();
		}else if (nextStep.toLowerCase().equals("accept")){
			String token = "accept!";
			System.out.println(token);
			MyFileManager.writeToken(token);
			notEnd = false;
		}else{//GOTO
			String token = "The input doesnot accord to the grammar!";
			System.out.println(token);
			MyFileManager.writeToken(token);
			notEnd = false;
		}
//		System.out.println("<״̬ջ��Ϊ�� " + statusStack.peek() + " ����ջ��Ϊ : " + symbolStack.peek() + " >");
	}
	
	private String searchTable(int status, char symbol) {
		String result = parsingTable.get(status).get(symbol);
		if(result == null){
			String token = "not find in parsing table: status: " + status + " symbol: " + symbol;
			System.out.println(token);
			MyFileManager.writeToken(token);
			return null;
		}
		return result;
	}
	

}
