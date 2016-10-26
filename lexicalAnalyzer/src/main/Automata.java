package main;

import java.util.ArrayList;

public class Automata {

	String presentStatus;
	ArrayList<String> TransformationTable;
	
	public void toNextStatus(char nextChar){
		for(String item: TransformationTable){
			if(item.startsWith(String.valueOf(presentStatus))){
				String[] list = item.split(" ");
				compare(nextChar, list[1]);
			}
		}
	}

	private boolean compare(char nextChar, String string) {
		// TODO 自动生成的方法存根
		return false;
	}
}
