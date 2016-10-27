package main;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		//delete output file
		MyFileManager.initFile();
		
		//Read from file
		ArrayList<String> input = MyFileManager.readFile("input.avaj");
		
		//read transformations
		ArrayList<String> translist = MyFileManager.readFile("Description of Automata.txt");
		
		Analyzer a = new Analyzer(translist);
		//put input into automata
		a.deal(input);
	}
	
}
	