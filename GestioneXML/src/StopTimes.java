import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class StopTimes extends FileXML {

    private List<String> trip_id = new ArrayList<>();
    private List<String> arrival_time = new ArrayList<>();
    private List<String> departure_time = new ArrayList<>();
    private List<String> stop_id = new ArrayList<>();
    private List<String> stop_sequence = new ArrayList<>();


    public StopTimes(String fileName) {
        super(fileName,"trip_id", "arrival_time", "departure_time", "stop_id", "stop_sequence");
        init();
    }
//LEGGE DAL FILE SOLO I TAG CHE CI INTERESSANO E SALVA I VALORI
    private void init() {
        int aggiungo = 0;

        try {
            XMLInputFactory xmlif=XMLInputFactory.newInstance();
            XMLStreamReader xmlr = xmlif.createXMLStreamReader(fileName, new FileInputStream(fileName));
            while(xmlr.hasNext()) {
                switch(xmlr.getEventType()) {
                    case XMLStreamConstants.START_DOCUMENT:
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        String startTag = xmlr.getLocalName();
                        if (startTag.equals(tags.get(0)))
                            aggiungo = 1;
                        if (startTag.equals(tags.get(1)))
                            aggiungo = 2;
                        if (startTag.equals(tags.get(2)))
                            aggiungo = 3;
                        if (startTag.equals(tags.get(3)))
                            aggiungo = 4;
                        if (startTag.equals(tags.get(4)))
                            aggiungo = 5;
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        switch(aggiungo){
                            case 1:
                                aggiungo = 0;
                                trip_id.add(xmlr.getText());
                                break;
                            case 2:
                                aggiungo = 0;
                                arrival_time.add(xmlr.getText());
                                break;
                            case 3:
                                aggiungo = 0;
                                departure_time.add(xmlr.getText());
                                break;
                            case 4:
                                aggiungo = 0;
                                stop_id.add(xmlr.getText());
                                break;
                            case 5:
                                aggiungo = 0;
                                stop_sequence.add(xmlr.getText());
                                break;
                            default:
                                break;
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

    public void printer(){
        System.out.println(trip_id);
        System.out.println(arrival_time);
        System.out.println(departure_time);
        System.out.println(stop_id);
        System.out.println(stop_sequence);
    }

    public List<String> getTrip_id() {
        return trip_id;
    }

    public List<String> getArrival_time() {
        return arrival_time;
    }

    public List<String> getDeparture_time() {
        return departure_time;
    }

    public List<String> getStop_id() {
        return stop_id;
    }

    public List<String> getStop_sequence() {
        return stop_sequence;
    }

//PASSANDO COME ARGOMENTO UN trip_id RITORNA L'INDICE A CUI SI TROVA NEL FILE StopsTimes
    public int trovaTripid(String id) {
        for (int i = 0; i < trip_id.size(); i++) {
            if (id.equals(trip_id.get(i)))
                return i;
        }
        return -1;
    }
}
