
import lombok.Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by Elior on 2016-08-07.
 */
public @Data class XMLCreator {
    public static void main(String argv[]) {
        createFile(1);
    }

    public static void createFile(int algNum){
        try {

            //create document
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element - Encryption
            Document doc = docBuilder.newDocument();
            Element Encryption = doc.createElement("Encryption");
            doc.appendChild(Encryption);

            // AlgorithmNumber elements
            Element AlgorithmNumber = doc.createElement("AlgorithmNumber");
            AlgorithmNumber.appendChild(doc.createTextNode(Integer.toString(algNum)));
            Encryption.appendChild(AlgorithmNumber);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("xmlFile.xml"));

            transformer.transform(source, result);


            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            // transformer.transform(source, result);
            //System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
