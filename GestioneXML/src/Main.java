
public class Main {

	public static void main(String[] args) {
		
	Trip viaggio = new Trip("trips.txt");
	Route tratta = new Route("routes.txt");
	Writer creaFile = new Writer();
	StopTimes fermate = new StopTimes("stop_times.txt");
	viaggio.printer();
	tratta.printer();
	fermate.printer();
	creaFile.write("Output.xml",viaggio,tratta,fermate);
	}
}
