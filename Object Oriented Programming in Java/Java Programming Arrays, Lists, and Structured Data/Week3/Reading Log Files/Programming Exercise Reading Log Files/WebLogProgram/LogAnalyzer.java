
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()) {
             LogEntry newLog = WebLogParser.parseEntry(line);
             records.add(newLog);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(int i = 0; i < records.size(); i++) {
             String ipAddress = records.get(i).getIpAddress();
             if(!uniqueIPs.contains(ipAddress)){
                 uniqueIPs.add(ipAddress);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for(int i = 0; i < records.size(); i++) {
             LogEntry logRecord = records.get(i);
             int statusCode = logRecord.getStatusCode();
             if(statusCode > num) {
                 System.out.println(logRecord);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) { // someday (MMM DD) like: “Dec 05”, “Apr 22”
         ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
         for(int i = 0; i < records.size(); i++) {
             LogEntry logRecord = records.get(i);
             String recordDay = logRecord.getAccessTime().toString().substring(4, 10);
             String ipAddress = logRecord.getIpAddress();
             if(recordDay.equals(someday) && (!uniqueIPsOnDay.contains(ipAddress))) {
                 uniqueIPsOnDay.add(ipAddress);
             }
         }
         return uniqueIPsOnDay;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
         for(int i = 0; i < records.size(); i++) {
             LogEntry logRecord = records.get(i);
             int statusCode = logRecord.getStatusCode();
             String ipAddress = logRecord.getIpAddress();
             if((low <= statusCode) && (statusCode <= high) && (!uniqueIPsInRange.contains(ipAddress))) {
                 uniqueIPsInRange.add(ipAddress);
             }
         }
         return uniqueIPsInRange.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> visitsMap = new HashMap<String, Integer>();
         for(int i = 0; i < records.size(); i++) {
             String ip = records.get(i).getIpAddress();
             if(!visitsMap.containsKey(ip)) {
                 visitsMap.put(ip, 1);
             } else {
                 visitsMap.put(ip, visitsMap.get(ip)+1);
             }
         }
         return visitsMap;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> visitsMap) {
          int maxNumVisits = 0;
          for(String ip : visitsMap.keySet()) {
              if(visitsMap.get(ip) > maxNumVisits) {
                  maxNumVisits = visitsMap.get(ip);
              }
          }
          return maxNumVisits;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> visitsMap) {
         ArrayList<String> mostVisitsList = new ArrayList<String>();
         int maxNumVisits = mostNumberVisitsByIP(visitsMap);
         for(String ip : visitsMap.keySet()) {
             if(visitsMap.get(ip) == maxNumVisits) {
                  mostVisitsList.add(ip);
              }
         }
         return mostVisitsList;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> iPForDaysList = new HashMap<String, ArrayList<String>>();
         for(int i = 0; i < records.size(); i++) {
             LogEntry logRecord = records.get(i);
             String recordDay = logRecord.getAccessTime().toString().substring(4, 10);
             String ipAddress = logRecord.getIpAddress();
             if(!iPForDaysList.containsKey(recordDay)) {
                 ArrayList<String> iPsList = new ArrayList<String>();
                 iPsList.add(ipAddress);
                 iPForDaysList.put(recordDay, iPsList);
             } else {
                ArrayList<String> iPsList = iPForDaysList.get(recordDay);
                iPsList.add(ipAddress);
                iPForDaysList.put(recordDay, iPsList); 
             }
         }
         return iPForDaysList;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPForDaysList) {
         String dayMostVisits = "";
         int maxVisits = 0;
         for(String recordDay : iPForDaysList.keySet()) {
             int currentVisits = iPForDaysList.get(recordDay).size();
             if(currentVisits > maxVisits) {
                 maxVisits = currentVisits;
                 dayMostVisits = recordDay;
             }
         }
         return dayMostVisits;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPForDaysList, String day) { // day MMM DD
         ArrayList<String> iPsForDayList = iPForDaysList.get(day);
         HashMap<String, Integer> visitsMap = new HashMap<String, Integer>();
         for(int i = 0; i < iPsForDayList.size(); i++) {
             String ip = iPsForDayList.get(i);
             if(!visitsMap.containsKey(ip)) {
                 visitsMap.put(ip, 1);
             } else {
                 visitsMap.put(ip, visitsMap.get(ip)+1);
             }
         }
         return iPsMostVisits(visitsMap);
     }
}
