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
	
	public void printOnConsole() {
		
		System.out.println("\n\n----------------------XML---------------------------");
    	System.out.print("<" + name);
    	for(Attribute att : attributes ) {
			System.out.print(" " + att.getName() + "=" + "\"" + att.getValue() + "\"");
		}
		System.out.println(">");
    	for(Element _row : subElements) {
    		System.out.print("\t<" + _row.getName());
    		for(Attribute att : _row.getAttributes()) {
    			System.out.print(" " + att.getName() + "=" + "\"" + att.getValue() + "\"");
    		}
    		System.out.println(">");
    		for (Element _genericItem : _row.getSubElements()) {
    			System.out.print("\t\t<" + _genericItem.getName());
    			for(Attribute att : _genericItem.getAttributes()) {
        			System.out.print(" " + att.getName() + "=" + "\"" + att.getValue() + "\"");
        		}
        		System.out.println(">" + _genericItem.getCharacter() + "</" + _genericItem.getName() + ">");
    		}
    		System.out.println("\t</" + _row.getName() + ">");
    	}
    	System.out.println("</" + name + ">");
	}
	

}
