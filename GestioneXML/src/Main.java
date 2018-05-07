import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//INIZIALIZZO FILE
		int scelta = 2;
		Scanner input = new Scanner(System.in);
		Trip viaggio = new Trip("trips.txt");
		Route tratta = new Route("routes.txt");
		StopTimes fermate = new StopTimes("stop_times.txt");
		Calendar_Dates date =new Calendar_Dates("calendar_dates.txt");
		//INIZIALIZZO WRITER
		Writer creaFile = new Writer();
		//SCRIVO FILE
		System.out.print("Inserire:\n0 per produrre il file completo di tutti i servizi \n1 per produrre il file filtrato con solo i servizi presenti in calendar_dates \n2 per produrre entrambi i files\n-> ");
		scelta = input.nextInt();
		switch (scelta){
			case 0:
				creaFile.write("SenzaCalendario.xml",viaggio,tratta,fermate);
				break;
			case 1:
				creaFile.write("ConCalendario.xml",viaggio,tratta,fermate,date);
				break;
			case 2:
				creaFile.write("ConCalendario.xml",viaggio,tratta,fermate,date);
				creaFile.write("SenzaCalendario.xml",viaggio,tratta,fermate);
				break;
		}
	}
}
