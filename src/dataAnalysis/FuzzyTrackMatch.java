package dataAnalysis;

/*CAUTION NEEDS A LOT OF WORK.
* 1. Needs better data validation
* 2. Needs more testing both on difference method and contains method for writers
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;




public class FuzzyTrackMatch {
	private static File sourceFile = new File(".\\data\\theirData.txt");
	private static File archiveFile = new File(".\\data\\ourData.txt");
	
	public ArrayList<MusicTrack> theirData;
	public ArrayList<MusicTrack> ourData;
	
	public ArrayList<String> resultsList = new ArrayList<String>();

	public int y = 0;
	public void fuzzyCompareLists() {
	
		for(MusicTrack nextTheirs: theirData){
			for(MusicTrack nextOurs: ourData){
			
				double difference = getDifference(nextTheirs.name, nextOurs.name);
				if(difference < 0.3){	
					String [] archiveWriters = nextOurs.writers.split("/");
					for(String nextWriter : archiveWriters){
//						
						if(nextTheirs.writers.contains(nextWriter)){
							resultsList.add(nextTheirs.id + "|" + 
											nextTheirs.name + "|" + 
											nextTheirs.writers+ "|" +
											nextOurs.id + "|" +
											nextOurs.name + "|" + 
											nextOurs.writers
											);
							
						}	
					}	
				}	//end difference
			}
		} // end outer for
	}
				
	
	
	public double getDifference(String src, String arc){
		double difference = StringUtils.getLevenshteinDistance(src, arc);
		return (difference / src.length());
	}
	
	
	
	//@SuppressWarnings("finally")
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
	
	
	public void writeToFile(ArrayList<String> listToWrite){
		File file = new File(".\\data\\output.txt");
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(file);
			out.write("Their data number of rows: " + theirData.size());
			out.write(System.getProperty("line.separator"));
			out.write("Our data number of rows: " + ourData.size());
			out.write(System.getProperty("line.separator"));
			out.write(System.getProperty("line.separator"));
			out.write("Their id|Their name|Their writers|Our id|Our name|Our writers");
			out.write(System.getProperty("line.separator"));
			
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
		
		theirData = createTracks(sourceFile);
		ourData =createTracks(archiveFile);
		
		
		fuzzyCompareLists();
		writeToFile(resultsList);		
	}
		
	
	
	public static void main(String args[]){
		new FuzzyTrackMatch().launch();
	}
	
}
