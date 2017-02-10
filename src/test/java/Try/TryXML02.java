package Try;

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
 * Created by alexanderboffin on 2/12/16.
 */
public class TryXML02 {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        //initialisation reading xml file
        File inputFile = new File("/Users/alexanderboffin/Documents/sandbox exercises/selenium/seleniumbasicexercises/data driven testing/dierengegevensxmlvers02.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        //Get the root of the document& use .getNodeName() to convert it to a string
        String root = doc.getDocumentElement().getNodeName();
        System.out.println(root);
        //
        NodeList Varslist=doc.getElementsByTagName("vars");

        // Trying out the different lines in the file
        //=>see if on level of vars
        Node Varsregel=Varslist.item(1);
        System.out.println(Varsregel.getNodeName());

        //getting to the attributes we want!!!!!!!
        Element TheAtribute=(Element) Varsregel;
        System.out.println(TheAtribute.getAttribute("subcategory"));


        //trying to get it in one go
        Element TheAtribute2=(Element) doc.getElementsByTagName("vars").item(1);

        System.out.println("trying something: "+TheAtribute2.getAttribute("subcategory"));

        //Further condensation:
        String TheAtribute3=((Element) doc.getElementsByTagName("vars").item(1)).getAttribute("subcategory");

        System.out.println("Further concentrated gives :"+TheAtribute3);

        //Checking out
        System.out.print("Have a nice day");//extra breakpoint
    }
}
