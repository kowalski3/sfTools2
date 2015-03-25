package fileAudit;

import java.util.ArrayList;

public class SflyFile{
	String fileName;
	int bin;
	int mp3;
	int xml;
	int guideWav;
	int masterWav;
	int kbp;
	int mp4;
	int unMatched;
	int cdg; //remove mp3 part of cdg from data as will conflict with xml mp3
	ArrayList<String> unmatchedData;
	
	public SflyFile(String fileName){
		unmatchedData = new ArrayList<String>();
		this.fileName = fileName;
		addFile(fileName);
	}
	
	public void addFile(String fileName){
		
		//change to switch statement
		if(fileName.contains(".bin")){
			bin ++;
		} else if(fileName.contains(".mp3")){
			mp3 ++;
		} else if(fileName.contains(".xml")){
			xml ++;
		} else if(fileName.contains("Guide")){
			guideWav ++;
		} else if (fileName.contains("_Master") || fileName.contains("_master")){
			masterWav ++;
		} else if (fileName.contains(".MP4")){
			mp4++;
		} else if (fileName.contains(".kbp")){
			kbp++;
		} else if (fileName.contains(".cdg")){
		    cdg++;
		} else{
			unMatched ++;
			unmatchedData.add(fileName);
		}
	}
	
	
	/*
	 * Returns pipe delimited string
	 */
	public String getFileData(){
		return 	fileName +
				"| bin: | " + bin + 
				"| mp3:| " + mp3 + 
				"| xml:| " + xml + 
				"| guide Wav:| " + guideWav + 
				"| masterWav:| " + masterWav + 
				"| kbp:| " + kbp +
				"|  mp4:| " + mp4 + 
				"| unMatched:| " + unMatched +
				"| unmatchedData:| " + unmatchedData.toString();
	}

}