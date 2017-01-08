package main;

import java.util.ArrayList;
import java.util.HashMap;

public class HandleRawData {

	public static HashMap<Integer, HashMap<Character, String>> handleParsingTable(ArrayList<String> parsingTable) {
		HashMap<Integer, HashMap<Character, String>> map = new HashMap<>();
		for(String str : parsingTable){
			String[] list = str.split(" ");
			if(map.containsKey(Integer.parseInt(list[0]))){
				HashMap<Character, String> s = map.get(Integer.parseInt(list[0]));
				s.put(list[1].charAt(0), list[2]);
			}else{
				HashMap<Character, String> s = new HashMap<>();
				map.put(Integer.parseInt(list[0]), s);
				s.put(list[1].charAt(0), list[2]);
			}
		}
		return map;
	}
}
