import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Trip extends FileXML {

    private List<String> route_id = new ArrayList<>();
    private List<String> service_id = new ArrayList<>();
    private List<String> trip_id = new ArrayList<>();
    private List<String> trip_short_name = new ArrayList<>();

    public Trip(String fileName) {
        super(fileName, "route_id", "service_id", "trip_id", "trip_short_name");
        init();
    }
    //LEGGE DAL FILE SOLO I TAG CHE CI INTERESSANO E SALVA I VALORI
    public void init(){
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
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        switch(aggiungo){
                            case 1:
                                aggiungo = 0;
                                route_id.add(xmlr.getText());
                                break;
                            case 2:
                                aggiungo = 0;
                                service_id.add(xmlr.getText());
                                break;
                            case 3:
                                aggiungo = 0;
                                trip_id.add(xmlr.getText());
                                break;
                            case 4:
                                aggiungo = 0;
                                trip_short_name.add(xmlr.getText());
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


    public List<String> getRoute_id() {
        return route_id;
    }

    public List<String> getService_id() {
        return service_id;
    }

    public List<String> getTrip_id() {
        return trip_id;
    }

    public List<String> getTrip_short_name() {
        return trip_short_name;
    }
}
