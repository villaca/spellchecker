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

/*
 * Created by Daniel Villaça on 18/11/2016.
 */
public class KeyboardLayoutReader {
   
    public KeyboardLayoutList loadFromFile(String s) {
        KeyboardLayoutList layoutList = new KeyboardLayoutList();
        
        try {

	File fXmlFile = new File(s);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	NodeList nList = doc.getElementsByTagName("layout");

	System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
                
                KeyboardLayout layout = new KeyboardLayout(); 

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			layout.setName(eElement.getAttribute("model"));
			layout.addLine(new KeyboardLine (eElement.getElementsByTagName("line").item(0).getTextContent()));
                        layout.addLine(new KeyboardLine (eElement.getElementsByTagName("line").item(1).getTextContent()));
                        layout.addLine(new KeyboardLine (eElement.getElementsByTagName("line").item(2).getTextContent()));
			

		}
                layoutList.add(layout);
	}
    } 
    catch (Exception e) 
    {
	e.printStackTrace();
    }
        
    return layoutList;

    }
    
}