package main;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		//delete output file
		MyFileManager.initFile();
		
		//Read from file
		ArrayList<String> input = MyFileManager.readFile("input.avaj");
		
		//read transformations
		ArrayList<String> parsingTable = MyFileManager.readFile("Parsing Table.txt");
		//read grammar
		ArrayList<String> grammar = MyFileManager.readFile("Grammar.txt");
		
		Analyzer a = new Analyzer(parsingTable,grammar);
		//put input into automata
		a.deal(input);
	}
	
}
	