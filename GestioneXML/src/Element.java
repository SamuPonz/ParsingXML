import java.util.ArrayList;

public class Element{
	
	private String name;
	private String character = null;

	ArrayList<Element> subElements = new ArrayList<Element>();
	
	public Element(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}
	
	

}
