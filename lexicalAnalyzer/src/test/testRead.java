package test;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import main.Analyzer;
import main.MyFileReader;
import main.Transformation;

public class testRead {

	@Test
	public void testReadInput() {
		ArrayList<String> input = MyFileReader.readFile("input.avaj");
		for (String str : input) {
			System.out.println(str);
		}
	}

	@Test
	public void testReadTransTable() {

		// read transformations
		ArrayList<String> translist = MyFileReader.readFile("Description of Automata.txt");

		Analyzer a = new Analyzer(translist);
		ArrayList<Transformation> t = a.getTransformList();
		for (Transformation tr : t) {
			System.out.println(tr);
		}

		ArrayList<String> el = a.getEndStatusList();
		for (String tr : el) {
			System.out.println(tr);
		}
	}

	@Test
	private void testAutomata() {
		ArrayList<String> input = MyFileReader.readFile("input.avaj");

		// read transformations
		ArrayList<String> translist = MyFileReader.readFile("Description of Automata.txt");

		Analyzer a = new Analyzer(translist);
		// get Token List
		a.deal(input);
	}
}
