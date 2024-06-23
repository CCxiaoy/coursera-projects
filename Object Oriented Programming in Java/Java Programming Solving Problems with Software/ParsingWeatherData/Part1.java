import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part1 {
    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar, String field) {
        double currentTemp = Double.parseDouble(currentRow.get(field));
        if(currentTemp != -9999) {
            if(smallestSoFar == null || 
            (currentTemp < Double.parseDouble(smallestSoFar.get(field))))
            {
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    
    public CSVRecord getSmallestTempOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar) {
        smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, "TemperatureF");
        return smallestSoFar;
    }
    
    public CSVRecord coldestHourFile(CSVParser parser) {
        CSVRecord coldestRecord = null;
        for(CSVRecord currentRecord : parser) {
            coldestRecord = getSmallestTempOfTwo(currentRecord, coldestRecord);
        }
        return coldestRecord;
    }
    
    public String fileWithColdestTemperature(DirectoryResource dr) {
        String fileName = "";
        CSVRecord coldestRecordSoFar = null;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRecord = coldestHourFile(parser);
            coldestRecordSoFar = getSmallestTempOfTwo(currentRecord, coldestRecordSoFar);
            if(coldestRecordSoFar == currentRecord) {
                fileName = f.getPath();
            }
        }
        return fileName;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for(CSVRecord record : parser) {
            String currentHimidityStr = record.get("Humidity");
            if(!currentHimidityStr.equals("N/A")) {
                if(smallestSoFar != null) {
                    double currentHimidity = Double.parseDouble(currentHimidityStr);
                    double hdSmallestSoFar = Double.parseDouble(smallestSoFar.get("Humidity"));
                    if(currentHimidity < hdSmallestSoFar) {
                        smallestSoFar = record;
                    }
                } else {
                    smallestSoFar = record;
                }
            }
        }
        return smallestSoFar;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRecord = lowestHumidityInFile(parser);
            if(lowestSoFar == null) {
                lowestSoFar = currentRecord;
            }
            double currentHumidity = Double.parseDouble(currentRecord.get("Humidity"));
            double lowestHumiditySoFar = Double.parseDouble(lowestSoFar.get("Humidity"));
            if(currentHumidity < lowestHumiditySoFar) {
                lowestSoFar = currentRecord;
            }
        }
        return lowestSoFar;
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double averageTemp = 0;
        double countor = 0;
        double total = 0;
        for(CSVRecord record : parser) {
            countor++;
            total+=Double.parseDouble(record.get("TemperatureF"));
        }
        averageTemp = total / countor;
        return averageTemp;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double averageTemp = 0;
        double countor = 0;
        double total = 0;
        for(CSVRecord record : parser) {
            double humidity = Double.parseDouble(record.get("Humidity"));
            if(humidity >= value) {
                countor++;
                total+=Double.parseDouble(record.get("TemperatureF"));
            }
        }
        if(countor == 0) {
            return -999.0;
        }
        averageTemp = total / countor;
        return averageTemp;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourFile(parser);
        System.out.println("Coldest Temp: " + coldestRecord.get("TemperatureF") + ", Coldest Time: " + coldestRecord.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        String filePath = fileWithColdestTemperature(dr);
        String fileName = filePath.substring(filePath.indexOf("weather-"), filePath.length());
        System.out.println("Coldest day was in file " + fileName);
        
        FileResource fr = new FileResource(filePath);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourFile(parser);
        System.out.println("Coldest temperature on that day was " + coldestRecord.get("TemperatureF"));
        
        parser = fr.getCSVParser();
        System.out.println("All the Temperatures on the coldest day were: ");
        for(CSVRecord record : parser) {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + averageTemp);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(averageTemp == -999.0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + averageTemp);
        }
    }
}
