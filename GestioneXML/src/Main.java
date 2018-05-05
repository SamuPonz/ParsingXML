
public class Main {

	public static void main(String[] args) {
		
	
		Reader lettore = new Reader();
		
		Element agency = lettore.read("agency.txt");
		Element trips = lettore.read("trips.txt");
		
		trips.printOnConsole();
		agency.printOnConsole();
		
		

	}

}
