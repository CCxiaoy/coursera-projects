
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    public void totalBirths (FileResource fr) {
        int totalBirths = 0; // num of birth
        int countorNames = 0; // num of name
        int totalBoys = 0; // num of boys
        int countorBoysNames = 0; // num of boys name
        int totalGirls = 0; // num of girls
        int countorGirlsNames = 0; // num of girls name
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            countorNames ++;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                countorBoysNames++;
            }
            else {
                totalGirls += numBorn;
                countorGirlsNames++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total Num of names = " + countorNames);
        System.out.println("female girls = " + totalGirls);
        System.out.println("total Num of female names = " + countorGirlsNames);
        System.out.println("male boys = " + totalBoys);
        System.out.println("total Num of male names = " + countorBoysNames);
    }
    
    // gender F for female, M for male
    public int getRank(int year, String name, String gender) {
        // String fileName = "yob" + year + "short.csv";
        // String filePrefix = "us_babynames/us_babynames_test/";
        String fileName = "yob" + year + ".csv";
        String filePrefix = "us_babynames/us_babynames_by_year/";
        String filePath = filePrefix + fileName;
        FileResource fr = new FileResource(filePath);
        // pass false to indicate no headers in this csv file
        int rank = 0;
        boolean found = false;
        for(CSVRecord record : fr.getCSVParser(false)) {
            String rowGender = record.get(1); // get gender
            String rowName = record.get(0); // get name
            if(rowGender.equals(gender)) {
                rank++;
                if(rowName.equals(name)) {
                    found = true;
                    break; // end loop
                }
            }
        }
        if(!found) {
            return -1; // if name doesn't exist, return -1
        } else {
            return rank;
        }
    }
    
    // gender F for female, M for male
    public String getName(int year, int rank, String gender) {
        // String fileName = "yob" + year + "short.csv";
        // String filePrefix = "us_babynames/us_babynames_test/";
        String fileName = "yob" + year + ".csv";
        String filePrefix = "us_babynames/us_babynames_by_year/";
        String filePath = filePrefix + fileName;
        FileResource fr = new FileResource(filePath);
        String name = "NO NAME";
        for(CSVRecord record : fr.getCSVParser(false)) {
            String rowGender = record.get(1); // gender
            String rowName = record.get(0); // name
            if(rowGender.equals(gender)) {
                rank--;
                if(rank == 0) {
                    name = rowName;
                }
            }
        }
        return name;
    }
    
    // gender F for female, M for male
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");
    }
    
    // gender F for female, M for male
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int rank = 0;
        int year = -1;
        for(File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int fileYear = Integer.parseInt(fileName.substring(3, 7));
            int fileRank = getRank(fileYear, name, gender);
            if(rank <= 0 || (fileRank > -1 && rank > fileRank)) {
                rank = fileRank;
                year = fileYear;
            }
        }
        return year;
    }
    
    // gender F for female, M for male
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double averageRank = -1.0;
        double totalRank = 0.0;
        double countor = 0.0;
        for(File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int fileYear = Integer.parseInt(fileName.substring(3, 7));
            int fileRank = getRank(fileYear, name, gender);
            if(fileRank > 0) {
                totalRank += fileRank;
                countor ++;
            }
        }
        if(totalRank > 0 && countor > 0) {
            averageRank = totalRank / countor;
        }
        return averageRank;
    }
    
    // gender F for female, M for male
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int total = 0;
        // String fileName = "yob" + year + "short.csv";
        // String filePrefix = "us_babynames/us_babynames_test/";
        String fileName = "yob" + year + ".csv";
        String filePrefix = "us_babynames/us_babynames_by_year/";
        String filePath = filePrefix + fileName;
        FileResource fr = new FileResource(filePath);
        for(CSVRecord record : fr.getCSVParser(false)) {
            String rowName = record.get(0); // name
            String rowGender = record.get(1); // gender
            int rowNum = Integer.parseInt(record.get(2)); // num
            if(rowGender.equals(gender)) {
                if(rowName.equals(name)) {
                    break;
                } else {
                    total+=rowNum;
                }               
            }
        }
        return total;
    }
    
    public void testTotalBirths () {
        // FileResource fr = new FileResource();
        // FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob1905.csv");
        // totalBirths(fr);
        
        // System.out.println(getRank(1971, "Frank", "M"));
        // System.out.println(getRank(2012, "Mason", "F"));
        
        // System.out.println(getName(1980, 350, "F"));
        // System.out.println(getName(2012, 6, "M"));
        
        // whatIsNameInYear("Owen", 1974, 2014, "M");
        
        System.out.println(yearOfHighestRank("Genevieve", "F"));
        
        // System.out.println(getAverageRank("Robert", "M"));
        // System.out.println(getAverageRank("Jacob", "M"));
        
        // System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}
