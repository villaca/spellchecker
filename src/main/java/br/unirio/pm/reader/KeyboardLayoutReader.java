package br.unirio.pm.reader;

import br.unirio.pm.model.KeyboardLayout;
import br.unirio.pm.model.KeyboardLayoutList;
import br.unirio.pm.model.KeyboardLine;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * this class reads the possibles Keyboard layout
 * 
 * @author Daniel Villaça
 */
public class KeyboardLayoutReader {
   
    /**
     * reads the XML content and stores the keyboard layouts in a list
     * 
     * @param sourceFile  file path
     * @return the list off layout characters
     * 
     * @return the list of possibles keyboard layouts
     */
    public KeyboardLayoutList loadFromFile(String sourceFile) {
        KeyboardLayoutList layoutList = new KeyboardLayoutList();
        
        try {

	        File fXmlFile = new File(sourceFile);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fXmlFile);

                doc.getDocumentElement().normalize();

	        NodeList nList = doc.getElementsByTagName("layout");

	        for (int j = 0; j < nList.getLength(); j++) 
                {
		    Node nNode = nList.item(j);
                    layoutList.add(readNode(nNode));
	        }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return layoutList;
    }

    /**
     * defines the keyboard model wanted and defines the lines
     * 
     * @param nNode  element of the xml file
     * @return the layout of the keyboard
     * 
     * @return the keyboard layout used
     */
    private KeyboardLayout readNode(Node nNode){

        KeyboardLayout layout = new KeyboardLayout();
        Element eElement = (Element) nNode;

        layout.setModel(eElement.getAttribute("model"));
        
        for(int i = 0; i < eElement.getElementsByTagName("line").getLength(); i++)
        {
            
            if(eElement.getElementsByTagName("line").item(i).hasAttributes())
            {
                String offset = eElement.getElementsByTagName("line")
                        .item(i).getAttributes().getNamedItem("offset").getNodeValue();

                String line = eElement.getElementsByTagName("line").item(i).getTextContent();
                layout.AddKeyboardLine(new KeyboardLine (line, Float.parseFloat(offset)));
            }
            else 
            {
                String line = eElement.getElementsByTagName("line").item(i).getTextContent();
                layout.AddKeyboardLine(new KeyboardLine (line));
            }
        }

        return layout;
    }
    
}