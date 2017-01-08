package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyFileManager {
	
	public static void initFile() {
		File file = new File(outputFile);
		file.delete();
	}

	public static ArrayList<String> readFile(String fileName){
		ArrayList<String> list = new ArrayList<>();
		File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            
            while ((tempString = reader.readLine()) != null) {
            	list.add(tempString.trim());
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
	
	private static final String outputFile = "output.txt";
	
	public static void writeToken(String token){
        File file = new File(outputFile);
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file,true);
            writer = new BufferedWriter(fw);
            writer.append(token);
            writer.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
