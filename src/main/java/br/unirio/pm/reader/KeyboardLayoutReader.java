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
 * Classe usada para ler o layout do teclado
 * 
 * @autor Daniel Villaça 
 * @version 18/11/2016.
 */
public class KeyboardLayoutReader {
   
    /**
     * @param sourceFile  caminho do arquivo
     */
    public KeyboardLayoutList LoadFromFile(String sourceFile) {
        KeyboardLayoutList layoutList = new KeyboardLayoutList();
        
        try {

	        File fXmlFile = new File(sourceFile);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fXmlFile);

                doc.getDocumentElement().normalize();

	        NodeList nList = doc.getElementsByTagName("layout");

	        for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
                layoutList.add(readNode(nNode));
	        }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return layoutList;
    }

    /**
     * @param nNode  ?
     */
    private KeyboardLayout readNode(Node nNode){

        KeyboardLayout layout = new KeyboardLayout();
        Element eElement = (Element) nNode;

        layout.setName(eElement.getAttribute("model"));
        for(int i = 0; i < eElement.getElementsByTagName("line").getLength(); i++){
            if(eElement.getElementsByTagName("line").item(i).hasAttributes()){
                String offset = eElement.getElementsByTagName("line")
                        .item(i).getAttributes().getNamedItem("offset").getNodeValue();

                String line = eElement.getElementsByTagName("line").item(i).getTextContent();
                layout.AddKeyboardLine(new KeyboardLine (line, Float.parseFloat(offset)));
            }
            else {
                String line = eElement.getElementsByTagName("line").item(i).getTextContent();
                layout.AddKeyboardLine(new KeyboardLine (line));
            }
        }

        return layout;
    }
    
}