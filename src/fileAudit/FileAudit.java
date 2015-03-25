package fileAudit;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/*
 * Analyses source contents and assess which files exist for each sku
 */

public class FileAudit{
	private File productData = new File("C:\\Julian\\git\\sfTools2\\data\\fileAuditData.txt");
	private Map<String,SflyFile> productMap = new HashMap<String, SflyFile>();
	

	public void readFile(){
		BufferedReader in = null;
		
		try{
		
			in = new BufferedReader(new FileReader(productData));
			String line;
			
			while((line = in.readLine()) !=null){
				String key = line.substring(0, 9);
				
				if(productMap.get(key) == null){
					productMap.put(key, new SflyFile(line));
				} else {
					productMap.get(key).addFile(line);
				}	
			}
		
		} catch(FileNotFoundException ex){
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
		}catch(IOException ex){
			ex.printStackTrace();
		}
	
	}
	
	
	public void writeMapToFile(){
		File file = new File("fileAuditOutput.txt");
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(file);
			for (Map.Entry<String, SflyFile> entry : productMap.entrySet()) {
				SflyFile tempValue = (SflyFile) entry.getValue();
				out.write(entry.getKey() + "| " +  tempValue.getFileData());
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
		
	
	private void launch(){
	
		readFile();
		writeMapToFile();
	}

	public static void main(String[] args){
		new FileAudit().launch();
	}
}