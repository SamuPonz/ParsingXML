import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class Reader {
	
	private final String ROW = "row";
	
	public Element read(String fileName) {
		
		//setup serve per controllare che il reader abbia visto il primo tag del file xml
		//imAtRow serve per controllare di essere al tag row
		//root viene inizializzato in cima e gli viene assegnato il valore del primo tag (ossia il root)
				
			boolean setup = true;
			boolean imAtRow = false;
			Element root = null;
			Element row = null;
			Element genericItem = null;
		    Attribute attribute = null;	
			
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
			return root;
			}

}
