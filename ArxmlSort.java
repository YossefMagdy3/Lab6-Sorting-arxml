import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArxmlSort {
    public static void main(String[] args) throws EmptyAutosarFileException, TransformerException, SAXException,
            IOException, ParserConfigurationException {

        try {

            String inputFileName = args[0];
            // extension test
            if (!(inputFileName.contains(".arxml"))) {
                throw new NotVaildAutosarFileException(
                        "Error: Not a valid file name (extension), please enter a valid name");
            }
            // arxml file as "File" object
            File arxmldoc = new File(inputFileName);

            // empty test
            if (arxmldoc.length() == 0) {
                throw new EmptyAutosarFileException("Input ARXML file is empty.");
            }

            DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbFact.newDocumentBuilder();
            Document doc = db.parse(arxmldoc);

            ArrayList<Element> container_Arr = new ArrayList<Element>();
            NodeList container_NodeList = doc.getElementsByTagName("CONTAINER");
            NodeList shortName_NodeList = doc.getElementsByTagName("SHORT-NAME");
            String[] shortName_Arr = new String[shortName_NodeList.getLength()];
            Node shortNameNode = null;
            shortName_Arr = getShortNameArray(shortName_NodeList, shortNameNode);

            Arrays.sort(shortName_Arr);

            for (int i = 0; i < shortName_Arr.length; i++) {

                for (int j = 0; j < container_NodeList.getLength(); j++) {

                    if (shortName_Arr[i].equals(shortName_NodeList.item(j).getTextContent())) {
                        container_Arr.add((Element) container_NodeList.item(j));

                    }

                }

            }
            Element rootElement = doc.getDocumentElement();

            for (Element e : container_Arr) {

                rootElement.appendChild(e);
            }

            String outputFile = getOutputFileName(inputFileName);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(outputFile));
            transformer.transform(source, result);
            System.out.println("File sorting done successfully, output file : " + outputFile);

        } catch (NotVaildAutosarFileException e) {
            System.out.println(e);
        }

    }

    private static String getOutputFileName(String inputFileName) {
        String outputFileNoExtension = null;
        String pattern = "(.*)\\."; // pattern to match everything before a dot

        // create a Pattern object with the pattern
        Pattern p = Pattern.compile(pattern);

        // create a Matcher object with the input string
        Matcher m = p.matcher(inputFileName);

        // check if there is a match
        if (m.find()) {
            outputFileNoExtension = m.group(1); // get the matched substring

        }
        String outputFile = outputFileNoExtension + "_mod.arxml";
        return outputFile;
    }

    private static String[] getShortNameArray(NodeList shortNameNodeList, Node shortNameNode) {
        String[] str = new String[shortNameNodeList.getLength()];
        for (int i = 0; i < shortNameNodeList.getLength(); i++) {

            shortNameNode = shortNameNodeList.item(i);

            str[i] = shortNameNode.getTextContent();

        }
        return str;

    }

}

class EmptyAutosarFileException extends Exception {

    public EmptyAutosarFileException(String message) {
        super(message);
    }
}

class NotVaildAutosarFileException extends Exception {
    public NotVaildAutosarFileException(String message) {
        super(message);
    }
}