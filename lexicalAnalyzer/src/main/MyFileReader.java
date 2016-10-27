package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyFileReader {

	public static ArrayList<String> readFile(String fileName){
		ArrayList<String> list = new ArrayList<>();
		File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            
            while ((tempString = reader.readLine()) != null) {
            	list.add(tempString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return list;
	}
}
