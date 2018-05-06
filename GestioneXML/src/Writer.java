
import java.io.FileWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;


public class Writer {

	public boolean write(String filename, Trip viaggio, Route tratta, StopTimes fermata){
		System.out.println("Scrittura su file");
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		int j;
		try {
			writer = output.createXMLStreamWriter(new FileWriter(filename));

			writer.writeStartDocument("utf-8","1.0");
			writer.writeStartElement("services");
			for(int i = 0; i < viaggio.getService_id().size(); i++){

				writer.writeStartElement("serivice");
				writer.writeAttribute("id",viaggio.getService_id().get(i));
				writer.writeStartElement("trip");
				String id = viaggio.getTrip_id().get(i);
				writer.writeAttribute("id",id);
				j = fermata.trovaTripid(id);
				writer.writeStartElement("stop");
				writer.writeAttribute("id", fermata.getStop_id().get(j));
				writer.writeEmptyElement("arrival\t" +  fermata.getArrival_time().get(j));
				writer.writeEmptyElement("departure\t" +  fermata.getDeparture_time().get(j));
				writer.writeEndElement();
				writer.writeEndElement();
				j = tratta.trovaRouteid(viaggio.getRoute_id().get(i));
				writer.writeStartElement("route");
				writer.writeAttribute("id", tratta.getRoute_id().get(j));
				writer.writeEmptyElement("tratta\t" +  tratta.getRoute_long_name().get(j));
				writer.writeEndElement();
				writer.writeEndElement();

			}
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.flush();
			writer.close();
			System.out.println("End!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print("Vecchio, problema!");
			e.printStackTrace();
			return false;
		}
		return true;
    }
}
