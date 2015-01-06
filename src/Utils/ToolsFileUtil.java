package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToolsFileUtil {
	
	public static String[][] csvTo2DArrayList(File dataFile){
		BufferedReader in = null;
		String[][] tempArray = new String[25000][3];
		
		try{	
			in = new BufferedReader(new FileReader(dataFile));
			String line;
			int i = 0;
			
			while ((line = in.readLine()) != null) {
				String[] tempLine = line.split("\t");
				tempArray[i][0] = tempLine[0];
				tempArray[i][1] = tempLine[1].toUpperCase();
				tempArray[i][2] = tempLine[2];
				
				i++;
				
			}
				
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		} finally{
			closeReader(in);
			return tempArray;
		}
		
	}
	
	
	private static void closeReader(Reader reader){
		try{
			if(reader != null){
				reader.close();
			}
			} catch(IOException ex){
				ex.printStackTrace();
		}
	}



}
