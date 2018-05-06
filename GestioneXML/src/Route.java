import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Route extends FileXML{

    private List<String> route_id = new ArrayList<>();
    private List<String> route_short_name = new ArrayList<>();
    private List<String> route_long_name = new ArrayList<>();

    public Route(String fileName) {
        super(fileName, "route_id", "route_short_name", "route_long_name");
        init();
    }
    //LEGGE DAL FILE SOLO I TAG CHE CI INTERESSANO E SALVA I VALORI
    public void init() {
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

                        break;
                    case XMLStreamConstants.CHARACTERS:
                        switch(aggiungo){
                            case 1:
                                aggiungo = 0;
                                route_id.add(xmlr.getText());
                                break;
                            case 2:
                                aggiungo = 0;
                                route_short_name.add(xmlr.getText());
                                break;
                            case 3:
                                aggiungo = 0;
                                route_long_name.add(xmlr.getText());
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
        System.out.println(route_id);
        System.out.println(route_short_name);
        System.out.println(route_long_name );

    }

    public List<String> getRoute_id() {
        return route_id;
    }

    public List<String> getRoute_long_name() {
        return route_long_name;
    }

    public List<String> getRoute_short_name() {
        return route_short_name;
    }

    public int trovaRouteid(String id) {
        for (int i = 0; i < route_id.size(); i++) {
            if(id.equals(route_id.get(i)))
                return i;
        }
        return -1;
    }
}

