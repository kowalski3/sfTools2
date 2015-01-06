import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class UniqueToProductMap{
	
	private File archiveCsv = new File("C:\\Julian\\git\\sfTools2\\data\\archiveCheck.csv");
	private Map<String, ArrayList<String>> archiveMap = new HashMap<String, ArrayList<String>>();

	
	
	

	private void csvToMap() {
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
		}
	}
	
	
	/*
	 * Main and launch
	 */
	public static void main(String[] args){
		new UniqueToProductMap().launch();	
	}

	private void launch() {
		csvToMap();
		
		for(Entry<String, ArrayList<String>> entry : archiveMap.entrySet()){
			System.out.println(entry);
		}
		
	}
}