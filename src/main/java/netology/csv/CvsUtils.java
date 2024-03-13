package netology.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import netology.Employee;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static netology.Main.writeString;
import static netology.json.json.listToJson;

public class csv {
    public static void writeCSV(String fileName, String[] data) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, true))) {
            csvWriter.writeNext(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void parseCSVtoJSON(String[] columnMapping, String filename, String jsonFileName) {
        try (CSVReader cvsReader = new CSVReader(new FileReader(filename))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> cvs = new CsvToBeanBuilder<Employee>(cvsReader)
                    .withMappingStrategy(strategy)
                    .build();
            String json = listToJson(cvs.parse());
            writeString(jsonFileName, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
