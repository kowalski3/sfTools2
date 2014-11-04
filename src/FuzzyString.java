import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import Utils.ToolsFileUtil;


public class FuzzyString {
	private static File sourceFile = new File("C:\\Julian\\git\\sfTools2\\data\\srcFuzzyData.csv");
	private static File refFile = new File("C:\\Julian\\git\\sfTools2\\data\\refFuzzyData.csv");
	
	public List<String> sourceList;
	public List<String> refList;
	public List<String> resultsList;

	
	public void fuzzyCompareLists(){
		
		for(String nextSource: sourceList){
			for (String nextRef: refList){
				int x = StringUtils.getLevenshteinDistance(nextSource,nextRef);
				
				if(x >= 1 && x <= 5) { 
					System.out.println("Source = " + nextSource + "\nRef = " + nextRef + "\n" + x +" difference\n\n");
				}
			}
		}
	}
	
	
	public void launch(){
		sourceList = ToolsFileUtil.singleColToList(sourceFile);
		refList = ToolsFileUtil.singleColToList(refFile);
		fuzzyCompareLists();
	}
		
		
	
	
	
	public static void main(String args[]){
		new FuzzyString().launch();
	}
	
}
