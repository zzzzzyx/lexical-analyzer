package main;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		//Read from file
		ArrayList<String> input = MyFileReader.readFile("input.avaj");
		for(String str : input){
			System.out.println(str);
		}
		
		Analyzer a = new Analyzer();
		//get Token List
		a.deal(input);
		
		//print Token
	}
	
}
	