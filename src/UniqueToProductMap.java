import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Utils.FileWriter;


public class UniqueToProductMap{
	
	private File archiveCsv = new File("C:\\Julian\\git\\sfTools2\\data\\archiveCheck.csv");
		
	/*
	 * Creates map internally and returns ArrayList, each line contains a key and a set of values.
	 */
	private ArrayList<String> csvToArrayList() {
		
		Map<String, ArrayList<String>> archiveMap = new HashMap<String, ArrayList<String>>();
		ArrayList<String> tempList = new ArrayList<String>();
		
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new FileReader(archiveCsv));
			String line;
			
			while((line = in.readLine()) != null){
				String[] dataRow = line.split(",");
				String key = dataRow[0];
				String value = dataRow[1];
			
				if(archiveMap.get(key) ==null) {
					archiveMap.put(key, new ArrayList<String>());
				}
		
				archiveMap.get(key).add(value);
			}
						
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			closeReader(in);
		}
		
		
		for(Entry<String, ArrayList<String>> entry : archiveMap.entrySet()){
			//if(entry.getKey() == null ||  entry.getValue() == null)
			tempList.add(entry.getKey() + entry.getValue());
		}
		
		return tempList;
		
	}
	private void closeReader(Reader reader){
		try{
			if(reader != null){
				reader.close();
			}
			} catch(IOException ex){
				ex.printStackTrace();
		}
	}


	/*
	 * Main and launch
	 */
	public static void main(String[] args){
		new UniqueToProductMap().launch();	
	}

	private void launch() {
		FileWriter.writeToFile(csvToArrayList()); 			
	}
}