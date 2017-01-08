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
	
	//处理一个一个字符组成的输入流
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
			//规约操作
			//首先将规约的表达式提取出来
			String expr = grammar.get(Integer.parseInt(nextStep.substring(1))).trim();
			
			String[] g = expr.split("=");
			//符号栈和状态栈弹出的次数相同
			for(int i = 0 ; i < g[1].length() ; i ++){
				symbolStack.pop();
				statusStack.pop();
			}
			
			//压入新的符号,且此符号肯定是非终结符
			symbolStack.push(g[0].charAt(0));
			
			//压入新的状态
			statusStack.push(Integer.parseInt(searchTable(statusStack.peek(),symbolStack.peek())));
			
			String token = "规约: 表达式编号: " + Integer.parseInt(nextStep.substring(1)) + " 表达式: " + expr;
			System.out.println(token);
			MyFileManager.writeToken(token);
		}else if (nextStep.toLowerCase().charAt(0) == 's'){
			//移入操作
			//将下一个状态（跟在s后面的数字）推入栈顶
			statusStack.push(Integer.parseInt(nextStep.substring(1)));
			symbolStack.push(c);
			String token = "移入: 移入符号: " + statusStack.peek() + " 移入状态: " + symbolStack.peek();
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
//		System.out.println("<状态栈顶为： " + statusStack.peek() + " 符号栈顶为 : " + symbolStack.peek() + " >");
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
