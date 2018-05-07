import java.util.*;

/**
 * La classe DataSet rappresenta l'insieme di dati contenuti all'interno di ogni file XML ricevuto in input.
 * E' costituita da un ArrayList che contiene i tags e da un ArrayList di ArrayList contenente tutti gli attributes,
 * ordinati secondo il tag a cui essi corrispondono.
 * 
 * @author Just E.A.T.
 *
 */
public class DataSet {
	
	ArrayList<String> tags = new ArrayList<String>();
	ArrayList<ArrayList<String>> elements = new ArrayList<ArrayList<String>>();
	
	public DataSet() {}
	
	/**
	 * Aggiunge un tag nell'ArrayList "tags"
	 * 
	 * @param toInsert
	 * @return
	 */
	public boolean addTag(String toInsert) {
		if(tags.contains(toInsert))
				return false;
		else {
			tags.add(toInsert);
			return true;
		}
	}
	
	/**
	 * Inizializza una riga dell'ArrayList "elements". Questo metodo viene chiamato quando
	 * per la prima volta il lettore incontra un tag non ancora aggiunto all'ArrayList "tags"
	 * 
	 * @param tag
	 */
	public void initializeRow(String tag) {
		ArrayList<String> row = new ArrayList<String>();
		elements.add(tags.indexOf(tag), row);
	}
	
	/**
	 * Aggiunge un elemento all'ArrayList "elements", ovviamente nel sub-ArrayList che gli compete
	 * 
	 * @param toInsert
	 * @param tag
	 */
	public void addRowElement(String toInsert, String tag) {
		elements.get(tags.indexOf(tag)).add(toInsert);
	}

}
