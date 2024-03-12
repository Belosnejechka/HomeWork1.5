package netology;

import java.io.*;
import java.util.List;

import static netology.csv.csv.parseCSVtoJSON;
import static netology.csv.csv.writeCSV;
import static netology.json.json.JSONToEmployees;
import static netology.xml.xml.createXMLFile;
import static netology.xml.xml.parseXMLtoJSON;

public class Main {
    public static void main(String[] args) {

        String[] employee = "1,John,Smith,USA,25".split(","), employee2 = "2,Ivan,Petrov,RU,23".split(",");
        String fileNameCSV = "data.csv", fileNameJSON = "data.json", fileNameXML = "data.xml", fileNameJSON2 = "data2.json";
        File dataCVS = new File(fileNameCSV), dataJSON = new File(fileNameJSON), dataJSON2 = new File(fileNameJSON2);
        try {
            dataCVS.createNewFile();
            dataJSON.createNewFile();
            dataJSON2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeCSV(fileNameCSV, employee);
        writeCSV(fileNameCSV, employee2);
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        parseCSVtoJSON(columnMapping, fileNameCSV, fileNameJSON);
        createXMLFile(fileNameXML);
        parseXMLtoJSON(fileNameXML, fileNameJSON2);
        String json = readString("data2.json");
        List<Employee> list = JSONToEmployees(json);
        System.out.println(list);
    }

    public static void writeString(String fileName, String data) {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readString(String fileName) {
        try (FileReader fileReader = new FileReader(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}