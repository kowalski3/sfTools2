//CAUTION NEEDS A LOT OF WORK!

import java.io.File;
import org.apache.commons.lang3.StringUtils;

import Utils.ToolsFileUtil;


public class FuzzyString {
	private static File sourceFile = new File("C:\\Julian\\git\\sfTools2\\data\\srcFuzzyData.txt");
	private static File archiveFile = new File("C:\\Julian\\git\\sfTools2\\data\\archiveFuzzyData.txt");
	
	public String[][] sourceList;
	public String[][] archiveList;
	//public List<String>resultsList;

	
	public void fuzzyCompareLists() {
		
		
		for(int i = 0; i < sourceList.length; i++){
			String src = sourceList[i][1];
			
			for(int j = 0; j < archiveList.length; j++){	
				String arcList = archiveList[j][1];
				
				if(src !=null && arcList !=null){
					int x = StringUtils.getLevenshteinDistance(src,arcList);
					int difference = 0;
					if(x > 0) difference = src.length() / x;  
						
						
						if(difference > 4){
						System.out.println("Source = " + sourceList[i][1] + " ---> " + sourceList[i][0]);
						
						System.out.println("Archive = " + archiveList[j][1] + " ---> " + archiveList[j][0]);
						System.out.println("Difference = " +  x);
						System.out.println("Source length = " + src.length());
						System.out.println("Difference / Source Length = " + src.length() /x);
						System.out.println("---------------------");
						System.out.println();
						
					}
				}
			}
		}
		
	}
	
	
	public void launch(){
		
		int x = StringUtils.getLevenshteinDistance("abc","add");
		
		
		sourceList = ToolsFileUtil.csvTo2DArrayList(sourceFile);
		archiveList =ToolsFileUtil.csvTo2DArrayList(archiveFile);
		fuzzyCompareLists();
	}
		
		
	
	
	
	public static void main(String args[]){
		new FuzzyString().launch();
	}
	
}
