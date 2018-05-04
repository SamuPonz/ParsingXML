
public class Main {

	public static void main(String[] args) {
		
		FileManager fileManager = new FileManager();
		
		System.out.println(fileManager.getFiles() + "\n");
		
		fileManager.read("trips.txt");
		
		

	}

}
