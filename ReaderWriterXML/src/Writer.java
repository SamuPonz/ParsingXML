import java.io.FileWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/**
 * Classe che si occupa di produrre il file XML da restituire come output
 * 
 * @author Just E.A.T.
 *
 */
public class Writer {
	
	public Writer() {}
	
	/**
	 * Metodo che stampa il file senza alterarne la formattazione originale (scarsa utilita')
	 * 
	 * @param data
	 * @param filename
	 * @return
	 */
	public boolean writeWithoutFormatting(DataSet data, String filename) {
		
		System.out.println("Writing in process...");
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		
		try {
			writer = output.createXMLStreamWriter(new FileWriter(filename));

			writer.writeStartDocument("utf-8","1.0");
			
			for(int i = 0; i < data.elements.get(0).size(); i++) {
				for(int j = 0; j < data.tags.size(); j++) {
					writer.writeStartElement(data.tags.get(j));
					if (!data.elements.get(j).isEmpty())
						writer.writeCharacters(data.elements.get(j).get(i));
					writer.writeEndElement();
				}				
			}
			
			writer.writeEndDocument();
			writer.flush();
			writer.close();
			
			System.out.println("End");
			
		} catch (Exception e) {
			
			System.out.print("Error");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Metodo che stampa il file secondo il modello proposto nella consegna del lavoro
	 * 
	 * @param filename
	 * @param file
	 * @return
	 */
	public boolean writeAccordingToFormat(String filename, DataSet ... file) { //Da passare in ordine: trips, stop_times, routes
		
		System.out.println("Writing in process...");
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		
		try {
			writer = output.createXMLStreamWriter(new FileWriter(filename));

			writer.writeStartDocument("utf-8","1.0");
			writer.writeStartElement("services");
			
			for(int i = 0; i < file[0].elements.get(4).size(); i++) {

				writer.writeStartElement("service"); //Start service
				writer.writeAttribute("id", file[0].elements.get(4).get(i));
				
				writer.writeStartElement("trip"); //Start trip
				String tripId = file[0].elements.get(1).get(i);
				writer.writeAttribute("id", tripId);
				
				writer.writeStartElement("stop"); //Start stop
				int position = file[1].elements.get(0).indexOf(tripId);
				String stopId = file[1].elements.get(6).get(position);
				writer.writeAttribute("id", stopId);
				
				writer.writeEmptyElement("arrival\t" + file[1].elements.get(2).get(position));
				writer.writeEmptyElement("departure\t" + file[1].elements.get(7).get(position));
				
				writer.writeEndElement(); //End stop
				writer.writeEndElement(); //End trip
				
				writer.writeStartElement("route"); //Start route
				String routeId = file[0].elements.get(3).get(i);
				writer.writeAttribute("id", routeId);
				
				int position2 = file[2].elements.get(0).indexOf(routeId);
				String tratta = file[2].elements.get(1).get(position2);
				if(tratta != null)
					writer.writeEmptyElement("tratta\t" + tratta);
				
				writer.writeEndElement(); //End route
				
				writer.writeEndElement(); //End service
			}
			
			writer.writeEndElement(); //End services
			writer.writeEndDocument();
			writer.flush();
			writer.close();
			System.out.println("End");
			
		} catch (Exception e) {
			System.out.print("Error");
			e.printStackTrace();
			return false;
		}
		return true;
    }
	
	/**
	 * Metodo che stampa solamente i dati contenuti nel file "calendar_dates.xml",
	 * sempre secondo la formattazione proposta.
	 * Fondamentalmente il metodo si comporta come "writeAccordingToFormat", 
	 * ma limitandosi ai services contenuti nel suddetto file, in accordo con
	 * la consegna (punto 2).
	 * 
	 * @param filename
	 * @param file
	 * @return
	 */
	public boolean writeCalendarDates(String filename, DataSet ... file) { //Da passare in ordine: trips, stop_times, routes, calendar_dates
		
		System.out.println("Writing in process...");
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		
		try {
			writer = output.createXMLStreamWriter(new FileWriter(filename));

			writer.writeStartDocument("utf-8","1.0");
			writer.writeStartElement("services");
			
			for(int i = 0; i < file[3].elements.get(1).size(); i++) {

				if(i > 0)
					if(file[3].elements.get(1).get(i).equals(file[3].elements.get(1).get(i-1)))
						continue;
				
				writer.writeStartElement("service"); //Start service
				String serviceToFind = file[3].elements.get(1).get(i);
				int position0 = file[0].elements.get(4).indexOf(serviceToFind);
				String serviceId = file[0].elements.get(4).get(position0);
				writer.writeAttribute("id", serviceId);
				
				writer.writeStartElement("trip"); //Start trip
				String tripId = file[0].elements.get(1).get(position0);
				writer.writeAttribute("id", tripId);
				
				writer.writeStartElement("stop"); //Start stop
				int position = file[1].elements.get(0).indexOf(tripId);
				String stopId = file[1].elements.get(6).get(position);
				writer.writeAttribute("id", stopId);
				
				writer.writeEmptyElement("arrival\t" + file[1].elements.get(2).get(position));
				writer.writeEmptyElement("departure\t" + file[1].elements.get(7).get(position));
				
				writer.writeEndElement(); //End stop
				writer.writeEndElement(); //End trip
				
				writer.writeStartElement("route"); //Start route
				String routeId = file[0].elements.get(3).get(position0);
				writer.writeAttribute("id", routeId);
				
				int position2 = file[2].elements.get(0).indexOf(routeId);
				String tratta = file[2].elements.get(1).get(position2);
				if(tratta != null)
					writer.writeEmptyElement("tratta\t" + tratta);
				
				writer.writeEndElement(); //End route
				
				writer.writeEndElement(); //End service
			}
			
			writer.writeEndElement(); //End services
			writer.writeEndDocument();
			writer.flush();
			writer.close();
			System.out.println("End");
			
		} catch (Exception e) {
			System.out.print("Error");
			e.printStackTrace();
			return false;
		}
		return true;
    }
}
