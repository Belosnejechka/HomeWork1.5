package netology.xml;

import netology.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static netology.Main.writeString;
import static netology.json.json.listToJson;

public class xml {
    public static void createXMLFile(String fileName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element staff = doc.createElement("staff");
            doc.appendChild(staff);
            Element employee = doc.createElement("employee");
            staff.appendChild(employee);
            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode("1"));
            employee.appendChild(id);
            Element firstName = doc.createElement("firstName");
            firstName.appendChild(doc.createTextNode("John"));
            employee.appendChild(firstName);
            Element lastName = doc.createElement("lastName");
            lastName.appendChild(doc.createTextNode("Smith"));
            employee.appendChild(lastName);
            Element country = doc.createElement("country");
            country.appendChild(doc.createTextNode("USA"));
            employee.appendChild(country);
            Element age = doc.createElement("age");
            age.appendChild(doc.createTextNode("25"));
            employee.appendChild(age);
            Element employee2 = doc.createElement("employee");
            staff.appendChild(employee2);
            Element id2 = doc.createElement("id");
            id2.appendChild(doc.createTextNode("2"));
            employee2.appendChild(id2);
            Element firstName2 = doc.createElement("firstName");
            firstName2.appendChild(doc.createTextNode("Ivan"));
            employee2.appendChild(firstName2);
            Element lastName2 = doc.createElement("lastName");
            lastName2.appendChild(doc.createTextNode("Petrov"));
            employee2.appendChild(lastName2);
            Element country2 = doc.createElement("country");
            country2.appendChild(doc.createTextNode("RU"));
            employee2.appendChild(country2);
            Element age2 = doc.createElement("age");
            age2.appendChild(doc.createTextNode("23"));
            employee2.appendChild(age2);

            DOMSource documentSource = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(documentSource, result);

        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static void parseXMLtoJSON(String xmlFileName, String jsonFileName) {
        try {
            List<Employee> employees = new ArrayList<>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(xmlFileName));
            NodeList staff = doc.getElementsByTagName("employee");
            for (int i = 0; i < staff.getLength(); i++) {
                Node node = staff.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    employees.add(new Employee(
                            (Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent())),
                            (element.getElementsByTagName("firstName").item(0).getTextContent()),
                            (element.getElementsByTagName("lastName").item(0).getTextContent()),
                            (element.getElementsByTagName("country").item(0).getTextContent()),
                            (Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()))
                    ));
                }
            }
            String json = listToJson(employees);
            writeString(jsonFileName, json);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }
}
