
import lombok.Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Elior on 2016-08-07.
 */
public @Data class XMLReader {
    public static void main(String argv[]) {
        returnAlgNum();
    }

    public static int returnAlgNum(){

        String ansString="";

        try {
            File xmlFile = new File("xmlFile.xml");

            //create document
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(xmlFile);


            NodeList list = doc.getElementsByTagName("Encryption");

            for(int i = 0; i<list.getLength(); i++){
                Node node = list.item(0);

                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    ansString = element.getElementsByTagName("AlgorithmNumber").item(0).getTextContent();
                }
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(ansString);

    }
}
