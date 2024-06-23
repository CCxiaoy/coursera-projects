import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part1 {
    public String countryInfo(CSVParser parser, String country) {
        String countryInfo = "NOT FOUND";
        for(CSVRecord record : parser) {
           String recordCountry = record.get("Country");
           if(recordCountry.contains(country)) {
               String recordExports = record.get("Exports");
               String recordValue = record.get("Value (dollars)");
               countryInfo = recordCountry + ": " + recordExports + ": " + recordValue; 
           }
        }
        return countryInfo;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for(CSVRecord record : parser) {
            String recordExports = record.get("Exports");
            if(recordExports.contains(exportItem1) && recordExports.contains(exportItem2)) {
                String recordCountry = record.get("Country");
                System.out.println(recordCountry);  
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int countor = 0;
        for(CSVRecord record : parser) {
            String recordExports = record.get("Exports");
            if(recordExports.contains(exportItem)) {
                countor++;
            }
        }
        return countor;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for(CSVRecord record : parser) {
            String recordValue = record.get("Value (dollars)");
            if(recordValue.length() > amount.length()) {
                String recordCountry = record.get("Country");
                System.out.println(recordCountry + " " + recordValue); 
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser1 = fr.getCSVParser();
        bigExporters(parser1, "$999,999,999,999");
        
        // FileResource fr = new FileResource();
        // CSVParser parser1 = fr.getCSVParser();
        // String countryInfo = countryInfo(parser1, "Germany");
        // System.out.println(countryInfo);
        
        // CSVParser parser2 = fr.getCSVParser();
        // listExportersTwoProducts(parser2, "cotton", "flowers");
        
        // CSVParser parser3 = fr.getCSVParser();
        // int countor = numberOfExporters(parser3, "cocoa");
        // System.out.println(countor);
        
        // CSVParser parser4 = fr.getCSVParser();
        // bigExporters(parser4, "$999,999,999");
    }
}
