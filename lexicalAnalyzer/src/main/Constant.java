package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Constant {
	
	public static final ArrayList<String> simpleSignList = new ArrayList<>();
	static{
		simpleSignList.add("+");
		simpleSignList.add("-");
		simpleSignList.add("*");
		simpleSignList.add("(");
		simpleSignList.add(")");
		simpleSignList.add("[");
		simpleSignList.add("]");
		simpleSignList.add("{");
		simpleSignList.add("}");
		simpleSignList.add("\"");
		simpleSignList.add(";");
		simpleSignList.add("!=");
		simpleSignList.add(",");
		
	}
	
	public static final ArrayList<String> remainedWordList = new ArrayList<>();
	static{
		remainedWordList.add("while");
		remainedWordList.add("if");
		remainedWordList.add("else");
		remainedWordList.add("break");
		remainedWordList.add("int");
		remainedWordList.add("void");
		remainedWordList.add("return");
		remainedWordList.add("printf");
		remainedWordList.add("scanf");
		remainedWordList.add("import");
	}
	
	//Directly return the upper case of the word
	public static String getTokenTypeFromRemainedWord(String remainedWord){
		if(remainedWordList.contains(remainedWord)){
			return "keyword_" + remainedWord;
		}else
			return "ID";
	}
	
	public static String getTokenTypeFromSign(String sign){
		
		switch(sign){
		case "+":return "PLUS";
		case "-":return "MINUS";
		case "*":return "MULT";
		case "/":return "DIV";
		case "=":return "ASSIGN";
		case "==":return "EQUAL";
		case ">":return "LT";
		case ">=":return "LTE";
		case "(":return "LS";
		case ")":return "RS";
		case ",":return "COMMA";
		case "\"":return "QUOTE";
		case "{":return "LB";
		case "}":return "RB";
		case "<":return "ST";
		case "<=":return "STE";
		case "[":return "LM";
		case "]":return "RM";
		case "!=":return "NEQ";
		case ";":return "SEMI";
		}
		
		return null;
	}
	
	public static final HashMap<String,String> reExplainer = new HashMap<>();
	
	static{
		reExplainer.put("simpleSign", "\\+|-|\\*|\\(|\\)|,|\\{|\\}|\\[|\\]|!=|;|\"|\\.");
		reExplainer.put("digit", "[0-9]");
		reExplainer.put("complexSign", "=|==|>|>=|<|<=");
		reExplainer.put("char", "[a-zA-Z]");
	}
}
