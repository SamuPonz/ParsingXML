
public class Main {

	public static void main(String[] args) {
		//INIZIALIZZO FILE
		Trip viaggio = new Trip("trips.txt");
		Route tratta = new Route("routes.txt");
		StopTimes fermate = new StopTimes("stop_times.txt");
		//INIZIALIZZO WRITER
		Writer creaFile = new Writer();
		//SCRIVO FILE
		creaFile.write("Output.xml",viaggio,tratta,fermate);
	}
}
