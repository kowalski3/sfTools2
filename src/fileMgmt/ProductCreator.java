package fileMgmt;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.io.*;


public class ProductCreator{
	
	private Map<String, Map<String, String>> compilationToProductsMap= new HashMap<String, Map<String, String>>();
	private File productData = new File("C:\\Julian\\git\\sfTools2\\data\\productData.csv");
	
	File sourceDirectory = new File("C:\\Users\\Julian.SUNFLYKARAOKE\\Desktop\\input");
	// get all the files from a directory
	File[] fList = sourceDirectory.listFiles();
	
	String targetDirectory = "C:\\Users\\Julian.SUNFLYKARAOKE\\Desktop\\output\\";
	
	
	
	//CREATE PRODUCT MAP FROM CSV DATA
	public void createProductMap(){
			BufferedReader in = null;
			try{	
				in = new BufferedReader(new FileReader(productData));
				String line;
				while ((line = in.readLine()) != null) {
					String[] dataRow = line.split(",");
					if(compilationToProductsMap.get(dataRow[0]) == null){		
						compilationToProductsMap.put(dataRow[0], new HashMap<String,String>());
					}
		
		compilationToProductsMap.get(dataRow[0]).put(dataRow[1], dataRow[2]);	
				}
					
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex){
				ex.printStackTrace();
			} finally{
				closeReader(in);
			}
	}
	private void closeReader(Reader reader){
		try{
			if(reader != null){
				reader.close();
			}
			} catch(IOException ex){
				ex.printStackTrace();
		}
	}
	
	
	
	//FIND AND COPY FILES
	public void findAndCopyFiles(String product){
		Map<String, String> tempMap = compilationToProductsMap.get(product);
		
		try{
			for(Map.Entry<String, String> entry : tempMap.entrySet()){
				
				File foundFile = findFile(entry.getKey());
				//WHY IS ABOVE RETURNING NULL FILES?
				System.out.println(entry.getKey());
		
				if(foundFile != null) copyFile(foundFile, entry.getValue());
				
				//System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
				
			}
		} catch(NullPointerException ex){
			System.out.println();
			System.out.println("Error, can't find " + product);
		} catch(IOException ex){
			System.out.println(ex);
		}

	}
	
	public File findFile(String filePrefix) {
		File foundFile = null;
		for (File file : fList) {
			if (file.isFile()) {
				if(file.getName().contains("SF" + filePrefix)){
						foundFile = file;
						break;
					}
			} else if (file.isDirectory()) {
				findFile(file.getPath());
			}	
		}
		return foundFile;
}
	
	public void copyFile(File sourceFile, String newFilePrefix) throws IOException {
		

		String sourceFileName = sourceFile.getName();
		String targetFileNameNoPrefix = sourceFileName.substring(sourceFileName.indexOf(" "));
		File destinationFile = new File(targetDirectory + newFilePrefix + targetFileNameNoPrefix);
		
		try {
			FileInputStream fileInputStream = new FileInputStream(sourceFile);
			FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);
			int bufferSize;
			byte[] bufffer = new byte[512];
			while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
				fileOutputStream.write(bufffer, 0, bufferSize);
			}
				fileInputStream.close();
				fileOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	
	
	//LAUNCH 
	private void launch(String product){
		
		createProductMap();
		findAndCopyFiles(product);
		
			
	}
	
	public static void main(String[] args){
		new ProductCreator().launch("SFDIGI-027");
		/*for(String next: args){
			new ProductCreator().launch(next);
		}*/
	}
}