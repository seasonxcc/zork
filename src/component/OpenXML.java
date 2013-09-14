package component;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OpenXML {
	Scanner source = new Scanner(System.in);
	public String userInput="";
	
	 /*Hashmaps to store the instance of the game*/
	public HashMap<String,Room> Rooms = new HashMap<String,Room>();
	public HashMap<String,Item> Items = new HashMap<String,Item>();
	public HashMap<String,Container> Containers = new HashMap<String,Container>();
	public HashMap<String,Creature> Creatures = new HashMap<String,Creature>();
	public HashMap<String,String> Inventory = new HashMap<String,String>();
	public HashMap<String,String> ObjectLookup = new HashMap<String,String>();
	public String currentRoom;
	public File file;
	
	public OpenXML(String filaname){
		int i,j,k,l,x,y,z;
		
		file = new File(filaname);
	    if (!file.canRead())
	    {//can't read file
	      System.out.println("Error opening file. Exiting...");
	      return;
	    }
	    
	    try
	    {/* Open the xml file*/
	        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        Document doc = (Document) builder.parse(file);

	        Element rootElement = (Element) ((org.w3c.dom.Document) doc).getDocumentElement();
	        
	        /* Every single first generation child is a room, container, creature, or item. So load them in*/
	        NodeList nodes = ((Node) rootElement).getChildNodes();
	        for(k=0;k<nodes.getLength();k++){
	        	Node node = nodes.item(k);
	            Element element;
	            
	            if (node instanceof Element)
	            {
	              element = (Element)node;
	              String tagType = ((org.w3c.dom.Element) element).getTagName();
	               
	              /*if it's a room*/
	              if(tagType.equals("room")){
	                  Room tempRoom = new Room();
	                  
	                  /*Get all possible Room attributes*/
	                  NodeList name = ((org.w3c.dom.Document) element).getElementsByTagName("name");
	                  tempRoom.name = (String) getString((Element)name.item(0));
	                  NodeList type = ((org.w3c.dom.Document) element).getElementsByTagName("type");
	                  
	                  if (type.getLength() > 0){
	                      tempRoom.type = (String) getString((Element)type.item(0));
	                  }else{
	                	  
	                  }
	              }
	              
	              }
	            
	        }
	    }
	    catch(Exception e){
	    	System.out.println("Invalid XML file, exiting");
	        System.exit(-1);
	    }//we are done with reading xml file
	}

	private Object getString(Element item) {
		// TODO Auto-generated method stub
		return null;
	}
}
