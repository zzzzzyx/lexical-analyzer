package main;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		//Read from file
		ArrayList<String> input = MyFileReader.readFile("input.avaj");
		
		//read transformations
		ArrayList<String> translist = MyFileReader.readFile("Description of Automata.txt");
		
		Analyzer a = new Analyzer(translist);
		//get Token List
		a.deal(input);
		
		//print Token
	}
	
}
	