import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class GestoreFile {
	
	private ArrayList<String> tags = new ArrayList<>();
	private ArrayList<String>[] dati;
	
	public GestoreFile(String nomeFile) {
		
		read(nomeFile);
		
	}

	public void read(String filename) {
		try {
			XMLInputFactory input = XMLInputFactory.newInstance();
			XMLStreamReader reader = input.createXMLStreamReader(filename, new FileInputStream(filename));
			
			boolean devoSalvare = false;
			boolean setup = true;
			
			while(reader.hasNext()) {
				switch(reader.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:
					break;
				case XMLStreamConstants.START_ELEMENT:
					if(setup) {
						if(devoSalvare) {
							tags.add(reader.getLocalName());
						}
					}
					if(reader.getLocalName().equals("row")) {
						devoSalvare = true;
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					if(setup) {
						if(reader.getLocalName().equals("row")) {
							setup = false;
						}
					}
					if(reader.getLocalName().equals("row")) {
						devoSalvare = false;
					}
					break;
				case XMLStreamConstants.NOTATION_DECLARATION:
					break;
				case XMLStreamConstants.CHARACTERS:
					break;
				default:
					break;
				}
			reader.next();
			}
			System.out.println(tags);
		}
		catch(Exception eccezione) {
			System.err.println("Errore");
			System.err.println(eccezione.getMessage());	
		}
	}
}

