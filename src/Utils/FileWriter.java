package Utils;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileWriter{
	
	public static void writeToFile(ArrayList<String> listToWrite){
		System.out.println("hi");
		File file = new File("output.txt");
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(file);
			for(String next : listToWrite){
				out.write(next);
				out.write(System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException ex) {
			// This happens if file does not exist and cannot be created,
			// or if it exists, but is not writable
			System.out.println("Cannot write to file " + file + ".");
		} finally {
			out.close();
		}
	}
	
	public static void writeToFile(Map<String, String> listToWrite){
		File file = new File("output.txt");
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(file);
			
			for(Map.Entry<String, String>  entry : listToWrite.entrySet()){
				out.write(entry.getValue());
				out.write(System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException ex) {
			// This happens if file does not exist and cannot be created,
			// or if it exists, but is not writable
			System.out.println("Cannot write to file " + file + ".");
		} finally {
			out.close();
		}
	}
	
}