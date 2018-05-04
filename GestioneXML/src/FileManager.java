import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class FileManager {
	
//	private final String TABLE = "table";
	private final String ROW = "row";
	private ArrayList<String> files = new ArrayList<String>();
	
	public FileManager() {
		
		filesInit();
	}
	
	public void filesInit() {
		files.add("agency.txt");
		files.add("calendar.txt");
		files.add("calendar_dates.txt");
		files.add("routes.txt");
		files.add("stop_times.txt");
		files.add("stops.txt");
		files.add("trips.txt");
	}
	
	public ArrayList<String> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<String> files) {
		this.files = files;
	}
    
	public void read(String searchedFile) {
		
//setup serve per controllare che il reader abbia visto il primo tag del file xml
//imAtRow serve per controllare di essere al tag row
//root viene inizializzato in cima e gli viene assegnato il valore del primo tag (ossia il root)
		
	boolean setup = true;
	boolean imAtRow = false;
	Element root = null;
	Element row = null;
	Element genericItem = null;
    Attribute attribute = null;	
	
	String fileName = "";
	
	for(String file : files) {
		if(file.equals(searchedFile))
			fileName = file;
		//gestire eccezzione se non viene trovato il file 
	}
	
	try {
			XMLInputFactory xmlif=XMLInputFactory.newInstance();
			XMLStreamReader xmlr = xmlif.createXMLStreamReader(fileName, new FileInputStream(fileName));
		    while(xmlr.hasNext()) {
		    	switch(xmlr.getEventType()) {
		        	case XMLStreamConstants.START_DOCUMENT:
//mi assicuro che setup sia settata a true e imAtRow sia settata a false nel momento in cui inizio a leggere il documento  	
		            	setup = true;	
		            	imAtRow = false;
		            	System.out.println("Start Read Doc " + fileName);
		            	break;
		            case XMLStreamConstants.START_ELEMENT:	            	
		            	String startTag = xmlr.getLocalName();
		            	if(setup) {
		            		root = new Element(startTag);
		            		for(int i = 0; i < xmlr.getAttributeCount(); i++) {
		            			attribute = new Attribute(xmlr.getAttributeLocalName(i));
				            	root.getAttributes().add(attribute);
				            	root.getAttributes().get(i).setValue(xmlr.getAttributeValue(i));
				            }
		            		setup = false;	
		            		}
		            	else {
			            	if(startTag.equals(ROW)) {
			            		row = new Element(startTag);
			            		for(int i = 0; i < xmlr.getAttributeCount(); i++) {
			            			attribute = new Attribute(xmlr.getAttributeLocalName(i));
					            	row.getAttributes().add(attribute);
					            	row.getAttributes().get(i).setValue(xmlr.getAttributeValue(i));
			            		}       		
			            		root.getSubElements().add(row);
			            		imAtRow = true;
			            	}
			            	else {
			            		imAtRow = false;
			            		genericItem = new Element(startTag);
			            		for(int i = 0; i < xmlr.getAttributeCount(); i++) {
			            			attribute = new Attribute(xmlr.getAttributeLocalName(i));
					            	genericItem.getAttributes().add(attribute);
					            	genericItem.getAttributes().get(i).setValue(xmlr.getAttributeValue(i));
			            		}
			            		row.getSubElements().add(genericItem);
			            	}
		            	}
		            	break;
		            case XMLStreamConstants.END_ELEMENT:
		            	String endTag = xmlr.getLocalName();
		            	if(endTag.equals(root.getName())) {
		            		System.out.println("End Read Doc " + fileName);
		            		printOnConsole(root);
		            	}
		            	break;
		            case XMLStreamConstants.NOTATION_DECLARATION:
		            	System.out.println("Inside "+xmlr.getText());
		            	break;	            	
		            case XMLStreamConstants.CHARACTERS:
		            	String character = xmlr.getText();
		            	if(character.trim().length()>0)
		            		if(setup) {
		            			root.setCharacter(character);
		            		}
		            	else {
			            	if(imAtRow) {
			            		row.setCharacter(character);			            		
			            	}
			            	else {
			            		genericItem.setCharacter(character);	
			            	}
		            	}
		            	break;
		            default:
		            	break;
		            }
		            xmlr.next();
		        }
		    }
	catch(Exception e){
		    	System.err.println("Error detected");
		    	System.err.println(e.getMessage());
		    }
	}
	

//stampa una bozza dei contenuti delle liste chiedendo come paramento la root
	public void printOnConsole(Element root) {
		
		System.out.println("\n\n---------XML---------");
    	System.out.println("<" + root.getName() + ">");
    	for(Element _row : root.getSubElements()) {
    		System.out.print("        <" + _row.getName());
    		for(Attribute att : _row.getAttributes()) {
    			System.out.print(" " + att.getName() + "=" + "\"" + att.getValue() + "\"");
    		}
    		System.out.println(">");
    		for (Element _genericItem : _row.getSubElements()) {
    			System.out.println("                <" + _genericItem.getName() + ">" + _genericItem.getCharacter() + "</" + _genericItem.getName() + ">");
    		}
    		System.out.println("        </" + _row.getName() + ">");
    	}
    	System.out.println("</" + root.getName() + ">");
	}
	
	public void write() {}
}
