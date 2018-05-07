/**
 * Classe contenente il metodo main: si occupa di istanziare oggetti di tipo DataSet per la
 * memorizzazione dei dati letti da una serie di files attraverso il metodo "explore" della classe Reader.
 * Successivamente la classe provvede a stampare i dati raccolti secondo i formati specificati nella
 * classe Writer (corrispondenti alle richieste 1 e 2).
 * 
 * @author Just E.A.T.
 *
 */
public class Main {

	public static void main(String[] args) {
		
		DataSet routesData = new DataSet();
		DataSet agencyData = new DataSet();
		DataSet calendarData = new DataSet();
		DataSet calendar_datesData = new DataSet();
		DataSet stop_timesData = new DataSet();
		DataSet stopsData = new DataSet();
		DataSet tripsData = new DataSet();
		
		Reader read_xml = new Reader();
		
		/*read_xml.explore(agencyData, "agency.xml"); //Files la cui lettura non è indispensabile per l'esecuzione del programma
		read_xml.explore(calendarData, "calendar.xml");
		read_xml.explore(stopsData, "stops.xml");*/
		read_xml.explore(stop_timesData, "stop_times.xml");
		read_xml.explore(tripsData, "trips.xml");
		read_xml.explore(routesData, "routes.xml");
		read_xml.explore(calendar_datesData, "calendar_dates.xml");
		
		Writer write_xml = new Writer();
		
		try {
			
			/*write_xml.writeWithoutFormatting(routesData, "routes_output.xml"); //Stampa (superflua ai fini dell'esercizio) dei dati nel
			write_xml.writeWithoutFormatting(agencyData, "agency_output.xml");   //medesimo formato secondo il quale sono stati letti
			write_xml.writeWithoutFormatting(calendarData, "calendar_output.xml");
			write_xml.writeWithoutFormatting(calendar_datesData, "calendar_dates_output.xml");
			write_xml.writeWithoutFormatting(stop_timesData, "stop_times_output.xml");
			write_xml.writeWithoutFormatting(stopsData, "stops_output.xml");
			write_xml.writeWithoutFormatting(tripsData, "trips_output.xml");*/
			
			write_xml.writeAccordingToFormat("final_output.xml", tripsData, stop_timesData, routesData);
			write_xml.writeCalendarDates("final_output_calendar_dates.xml", tripsData, stop_timesData, routesData, calendar_datesData);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}