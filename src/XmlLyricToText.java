import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.ArrayList;
//http://www.freeformatter.com/xpath-tester.html


//use rar reading methods to read file from directory and feed to module
public class XmlLyricToText {
	
	public void writeToFile(String fileName, ArrayList<String> songLyrics){
		File file = new File(fileName + ".txt");
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(file);
			for(String next : songLyrics){
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
	
	
	private void getLyricsInXml(String fileName)throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(fileName);
		
		XPathFactory xpathfactory = XPathFactory.newInstance();
		XPath xpath = xpathfactory.newXPath();
		
		// 1) Get all Text Elements
		XPathExpression expr = xpath.compile("//Text/text()");
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		
		ArrayList<String> songLyrics =  new ArrayList<String>();
		for (int i = 0; i < nodes.getLength(); i++) {
			if(containsLyrics(nodes.item(i).getNodeValue())){
				songLyrics.add(nodes.item(i).getNodeValue());
			}
			
		}
		
		writeToFile(fileName, songLyrics);
	}
	
	public boolean containsLyrics(String lyricLine){
		if(lyricLine.equals("Instrumental")
		|| lyricLine.contains("seconds")){
			return false;
		}
		return true;
		
	}
	
	private void traverseFolders(String startDirectory) throws Exception{
	
		File directory = new File(startDirectory);

		// get all the files from a directory
		File[] fList = directory.listFiles();
		
		for (File file : fList) {
			if (file.isFile()) {
				
				if(file.getName().contains(".xml")) {
					getLyricsInXml(file.getAbsolutePath());
				}
				
			} else if (file.isDirectory()) {
				traverseFolders(file.getPath());
			}
		}	
	}
	
	public static void main(String[] args) throws Exception {
		new XmlLyricToText().traverseFolders("C:\\Users\\Julian.SUNFLYKARAOKE\\Desktop\\TEST");
	}
}


