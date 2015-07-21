package dataAnalysis;
public class MusicTrack {

	public String id;
	public String name;
	public String writers;
	
	public MusicTrack(String id, String name, String writers){
		
		this.id = id;
		this.name = name;
		this.writers = writers;
	}
	
	 @Override 
	 public String toString(){
		return id + " , " + name + " ," + writers;
	}
	
}
