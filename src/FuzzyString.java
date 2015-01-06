//CAUTION NEEDS A LOT OF WORK!
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import Utils.ToolsFileUtil;



public class FuzzyString {
	private static File sourceFile = new File("C:\\Julian\\git\\sfTools2\\data\\srcFuzzyData.txt");
	private static File archiveFile = new File("C:\\Julian\\git\\sfTools2\\data\\archiveFuzzyData.txt");
	
	public ArrayList<MusicTrack> sourceList;
	public ArrayList<MusicTrack> archiveList;
	
	public ArrayList<String> resultsList = new ArrayList<String>();
	private Integer x;;

	
	public void fuzzyCompareLists() {
		
		for(MusicTrack nextSrc: sourceList){
			
			for(MusicTrack nextArc: archiveList){
			
				double difference = getDifference(nextSrc.name, nextArc.name);
				//System.out.println(difference);
				if(difference < 0.3){	
					String [] archiveWriters = nextArc.writers.split("/");
					
					for(String nextWriter : archiveWriters){
						
						if(nextSrc.writers.contains(nextWriter)){
							resultsList.add(nextSrc.name + "|" + 
											nextArc.name + "|" + 
											nextSrc.id + "|" + 
											nextArc.id + "|" +
											nextSrc.writers+ "|" +
											nextArc.writers
											);
							
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
	
	
	
	public ArrayList<MusicTrack> createTracks(File dataFile){
		
		BufferedReader in = null;
		ArrayList<MusicTrack> tempTrackList = new ArrayList<MusicTrack>();
		
		try{	
			in = new BufferedReader(new FileReader(dataFile));
			String line;
		
			
			while ((line = in.readLine()) != null) {
				String[] tempLine = line.split("\t");
				tempTrackList.add(new MusicTrack(tempLine[0], tempLine[1].toUpperCase(),tempLine[2].toUpperCase()));
			}
				
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		} finally{
			closeReader(in);
			return tempTrackList;
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
		sourceList = createTracks(sourceFile);
		archiveList =createTracks(archiveFile);
		
		//System.out.println(sourceList.size());
		//System.out.println(archiveList.size());
		
		fuzzyCompareLists();
		writeToFile(resultsList);		
	}
		
	
	
	public static void main(String args[]){
		new FuzzyString().launch();
	}
	
}
