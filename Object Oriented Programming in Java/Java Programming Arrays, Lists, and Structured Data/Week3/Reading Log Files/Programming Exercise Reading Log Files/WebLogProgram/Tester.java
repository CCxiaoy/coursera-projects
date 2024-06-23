
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("Number of unique IP: " + uniqueIPs);
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
        System.out.println("----------");
        // la.printAllHigherThanNum(200);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> uniqueIPsOnDay1 = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println(uniqueIPsOnDay1.size());
        System.out.println("----------");
        // ArrayList<String> uniqueIPsOnDay2 = la.uniqueIPVisitsOnDay("Sep 30");
        // System.out.println(uniqueIPsOnDay2.size());
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        // int uniqueIPs1  = la.countUniqueIPsInRange(200,299);
        // System.out.println("Number of unique IP in range 200-299: " + uniqueIPs1);
        int uniqueIPs2 = la.countUniqueIPsInRange(200,299);
        System.out.println("Number of unique IP in range 200-299: " + uniqueIPs2);
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> visitsMap = la.countVisitsPerIP();
        System.out.println(visitsMap);
    }
    
    public void testmostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> visitsMap = la.countVisitsPerIP();
        int mostNumVisits = la.mostNumberVisitsByIP(visitsMap);
        System.out.println(mostNumVisits); // expect 3
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> visitsMap = la.countVisitsPerIP();
        ArrayList<String> mostVisitsList = la.iPsMostVisits(visitsMap);
        for(int i = 0; i < mostVisitsList.size(); i++) {
            System.out.println(mostVisitsList.get(i));
        }
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer(); 
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> iPForDaysList = la.iPsForDays(); 
        System.out.println(iPForDaysList);
        // Expect: Sep 14 maps to one IP address, 
        // Sep 21 maps to four IP addresses, 
        // and Sep 30 maps to five IP addresses
    }
    
    public void testdayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer(); 
        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> iPForDaysList = la.iPsForDays(); 
        String dayMostVisits = la.dayWithMostIPVisits(iPForDaysList);
        System.out.println(dayMostVisits); // Expect: Sep 30
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer(); 
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> iPForDaysList = la.iPsForDays();
        ArrayList<String> iPsWithMostVisitsOnDayList = la.iPsWithMostVisitsOnDay(iPForDaysList, "Sep 29");
        for(int i = 0; i < iPsWithMostVisitsOnDayList.size(); i++) {
            System.out.println(iPsWithMostVisitsOnDayList.get(i));
        }
        // Expect: 61.15.121.171 and 177.4.40.87
    }
}
