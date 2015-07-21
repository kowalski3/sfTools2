package dataAnalysis;

//CAUTION NEEDS A LOT OF WORK!
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;




//compare two maps, note that key (Track) may contain multiple tracks so if there is a match 
// need to iterate collection to see if any writers match

public class NonFuzzyMatcher {
	private static File sourceFile = new File("C:\\Julian\\git\\sfTools2\\data\\srcFuzzyData.txt");
	private static File archiveFile = new File("C:\\Julian\\git\\sfTools2\\data\\archiveFuzzyData.txt");
	
	public Map<String, List<MusicTrack>> sourceMap;
	public Map<String, List<MusicTrack>> archiveMap;
	
	public ArrayList<String> resultsList = new ArrayList<String>();

	public int y = 0;
	
				
	public void fuzzyCompareLists(){
		for(Entry<String, List<MusicTrack>> value: archiveMap.entrySet()){	
			
			
			if(sourceMap.get(value.getKey()) != null){
				for(MusicTrack nextSrc: sourceMap.get(value.getKey())){
					for(MusicTrack nextArchive: value.getValue()){
						String[] archiveWriters = nextArchive.writers.split("/");
							for(String nextWriter: archiveWriters){
								if(nextSrc.writers.contains(nextWriter)|| nextSrc.writers.contains("BLANK")){
									resultsList.add(nextSrc.toString());
									//resultsList.add("ARC " + nextArchive.toString());
								}
							}
					}
				}	
			}
		}	
	}
	
	public double getDifference(String src, String arc){
		double difference = StringUtils.getLevenshteinDistance(src, arc);
		return (difference / src.length());
	}
	
	
	
	public Map<String, List<MusicTrack>> createTracks(File dataFile){
		
		Map<String, List<MusicTrack>> tempMap = new HashMap<String, List<MusicTrack>>();	
		BufferedReader in = null;
		
		try{	
			in = new BufferedReader(new FileReader(dataFile));
			String line;
		
			
			while ((line = in.readLine()) != null) {
				String[] tempLine = line.split("\t");
				if(tempMap.get(tempLine[1].toUpperCase()) == null){
					tempMap.put(tempLine[1].toUpperCase(),new ArrayList<MusicTrack>());
				}
				tempMap.get(tempLine[1].toUpperCase()).add(new MusicTrack(tempLine[0], tempLine[1].toUpperCase(),tempLine[2].toUpperCase()));
			}
				
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		} finally{
			closeReader(in);
			return tempMap;
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
	
	
	public static void writeToFile(ArrayList<String> listToWrite){
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
	
	
	
		
	
	
	
	public void launch(){
		
		sourceMap = createTracks(sourceFile);
		archiveMap =createTracks(archiveFile);
		
//		System.out.println(sourceMap.size());
//		System.out.println(archiveMap.size());
		
		
		
		fuzzyCompareLists();
		writeToFile(resultsList);		
	}
		
	
	
	public static void main(String args[]){
		new NonFuzzyMatcher().launch();
	}
	
}
