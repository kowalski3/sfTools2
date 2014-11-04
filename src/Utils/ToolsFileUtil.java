package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class ToolsFileUtil {
	
	public static ArrayList<String> singleColToList(File dataFile){
		BufferedReader in = null;
		ArrayList<String> tempList = new ArrayList<String>();
		try{	
			in = new BufferedReader(new FileReader(dataFile));
			String line;
			
			while ((line = in.readLine()) != null) {
				tempList.add(line);
			}
				
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		} finally{
			closeReader(in);
			return tempList;
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
