
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
public @Data class XMLReportCreator {
    public static void main(String argv[]) {
        createFile("f", "Encryption", "failed", "3", "11", "22", "33");
    }

    public static void createFile(String fileNameString, String encOrDecString, String statusString, String timeString, String Exception_nameString, String Exception_massageString, String Exception_StacktraceString){
        try {

            //create document
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element - Encryption
            Document doc = docBuilder.newDocument();
            Element Encryption = doc.createElement(encOrDecString + "_" + fileNameString);
            doc.appendChild(Encryption);

            // status elements
            Element status = doc.createElement("status");
            status.appendChild(doc.createTextNode(statusString));
            Encryption.appendChild(status);

            if(statusString.equals("succeeded")) {
                // status succeeded - time elements
                Element time = doc.createElement("time");
                time.appendChild(doc.createTextNode(timeString));
                Encryption.appendChild(time);
            }
            else if(statusString.equals("failed")){
                // status failed - Exception_name elements
                Element Exception_name = doc.createElement("Exception_name");
                Exception_name.appendChild(doc.createTextNode(Exception_nameString));
                Encryption.appendChild(Exception_name);

                // status failed - Exception_massage elements
                Element Exception_massage = doc.createElement("Exception_massage");
                Exception_massage.appendChild(doc.createTextNode(Exception_massageString));
                Encryption.appendChild(Exception_massage);

                // status failed - Exception_Stacktrace elements
                Element Exception_Stacktrace = doc.createElement("Exception_Stacktrace");
                Exception_Stacktrace.appendChild(doc.createTextNode(Exception_StacktraceString));
                Encryption.appendChild(Exception_Stacktrace);
            }
            else{
                System.out.println("This should not happen");
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File("XML report " + fileNameString + " " + encOrDecString + ".xml"));

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
