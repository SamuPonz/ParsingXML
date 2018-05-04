import java.util.ArrayList;

public class Element{
	
	private String name;
	private String character = null;
	
    private ArrayList<Attribute> attributes = new ArrayList<Attribute>();
	private ArrayList<Element> subElements = new ArrayList<Element>();
	
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

	public ArrayList<Element> getSubElements() {
		return subElements;
	}

	public void setSubElements(ArrayList<Element> subElements) {
		this.subElements = subElements;
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	

}
