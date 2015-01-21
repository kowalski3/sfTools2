package fileAudit;
public class SflyFile{
	int bin;
	int mp3;
	int xml;
	int guideWav;
	int masterWav;
	int mp4;
	int unMatched;
	
	public SflyFile(String fileName){
		addFile(fileName);
	}
	
	public void addFile(String fileName){
		
		if(fileName.contains(".bin")){
			bin ++;
		} else if(fileName.contains(".mp3")){
			mp3 ++;
		} else if(fileName.contains(".xml")){
			xml ++;
		} else if(fileName.contains("_Guide.wav")){
			guideWav ++;
		} else if (fileName.contains("_Master.wav")){
			masterWav ++;
		} else if (fileName.contains(".MP4")){
			mp4++;
		} else{
			unMatched ++;
		}
	}
	
	public String getFileData(){
		return 	"bin:, " + bin + 
				", mp3:, " + mp3 + 
				", xml:, " + xml + 
				", guide Wav:, " + guideWav + 
				", masterWav:, " + masterWav + 
				",  mp4:, " + mp4 + 
				", unMatched:, " + unMatched;
	}

}