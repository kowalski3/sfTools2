package fileAudit;

import java.util.ArrayList;

public class SflyFile{
	int bin;
	int mp3;
	int xml;
	int guideWav;
	int masterWav;
	int kbp;
	int mp4;
	int unMatched;
	ArrayList<String> unmatchedData;
	
	public SflyFile(String fileName){
		unmatchedData = new ArrayList<String>();
		addFile(fileName);
	}
	
	public void addFile(String fileName){
		
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
		}else if (fileName.contains(".kbp")){
			kbp++;
		}else{
			unMatched ++;
			unmatchedData.add(fileName);
		}
	}
	
	
	
	public String getFileData(){
		return 	"bin:, " + bin + 
				", mp3:, " + mp3 + 
				", xml:, " + xml + 
				", guide Wav:, " + guideWav + 
				", masterWav:, " + masterWav + 
				", kbp:, " + kbp +
				",  mp4:, " + mp4 + 
				", unMatched:, " + unMatched +
				", unmatchedData:, " + unmatchedData.toString();
	}

}