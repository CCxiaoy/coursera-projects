
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIdx = from;
        for(int i = from+1; i < quakeData.size(); i++) {
            if(quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) { // selection sort
        for(int i = 0; i < 50; i++) {
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i, qmax);
            in.set(maxIdx, qi);
        }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        int endPoint = quakeData.size()-numSorted-1;
        for(int i = 0; i < endPoint; i++) {
            QuakeEntry qei = quakeData.get(i);
            QuakeEntry qeiP1 = quakeData.get(i+1);
            if(qei.getMagnitude() > qeiP1.getMagnitude()) {
                quakeData.set(i, qeiP1);
                quakeData.set(i+1, qei);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        // for(QuakeEntry qe : in) {
        //    System.out.println(qe);
        // }
        
        int loopCountor = 0;
        while(loopCountor < in.size()-1) {
            onePassBubbleSort(in, loopCountor);
            
            // if(loopCountor < in.size()-2) {
            //    System.out.println("Printing Quakes after pass " + loopCountor);
            //    for(QuakeEntry qe : in) {
            //        System.out.println(qe);
            // }
            // } else {
            //    System.out.println("EarthQuakes in sorted order: ");
            // }
            
            loopCountor++;
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        boolean inMagOrder = true;
        for(int i = 0; i < quakes.size() - 1; i++) {
            double magi = quakes.get(i).getMagnitude();
            double magiP1 = quakes.get(i+1).getMagnitude();
            if(magi > magiP1) {
                inMagOrder = false;
            }
        }
        return inMagOrder;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) { // bubble sort
        int loopCountor = 0;
        while(loopCountor < in.size()-1) {
            onePassBubbleSort(in, loopCountor);
            
            loopCountor++;
            
            if(checkInSortedOrder(in)) {
                System.out.println(loopCountor + " were needed to sort the elements");
                break;
            }
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) { // selection sort
        int i = 0;
        while (i< in.size()) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            
            i++;
            if(checkInSortedOrder(in)) {
                System.out.println(i + " were needed to sort the elements");
                break;
            }
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        // String source = "data/nov20quakedatasmall.atom";
        // String source = "data/nov20quakedata.atom";
        // String source = "data/earthquakeDataSampleSix1.atom";
        // String source = "data/earthquakeDataSampleSix2.atom";
        // String source = "data/earthQuakeDataDec6sample1.atom";
        // String source = "data/earthQuakeDataDec6sample2.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
    
        System.out.println("read data for "+list.size()+" quakes");    
        
        // sortByMagnitude(list);
        // sortByLargestDepth(list);
        // sortByMagnitudeWithBubbleSort(list);
        // sortByMagnitudeWithCheck(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        
        // for (QuakeEntry qe: list) { 
        //    System.out.println(qe);
        // } 
        
        // String source1 = "data/earthquakeDataSampleSix1.atom";
        // ArrayList<QuakeEntry> list1  = parser.read(source1);  
        // System.out.println("read data for "+list1.size()+" quakes"); // should sort after 2 passes
        // sortByMagnitudeWithBubbleSortWithCheck(list1);
        // for (QuakeEntry qe: list1) { 
        //    System.out.println(qe);
        // } 
        
        // String source2 = "data/earthquakeDataSampleSix2.atom";
        // ArrayList<QuakeEntry> list2  = parser.read(source2); 
        // System.out.println("read data for "+list2.size()+" quakes"); // should sort after 3 passes
        // sortByMagnitudeWithBubbleSortWithCheck(list2);
        // for (QuakeEntry qe: list2) { 
        //    System.out.println(qe);
        // } 
        
        // String source1 = "data/earthquakeDataSampleSix1.atom";
        // ArrayList<QuakeEntry> list1  = parser.read(source1);  
        // System.out.println("read data for "+list1.size()+" quakes"); // should sort after 3 passes
        // sortByMagnitudeWithCheck(list1);
        // for (QuakeEntry qe: list1) { 
        //    System.out.println(qe);
        // } 
        
        // String source2 = "data/earthquakeDataSampleSix2.atom";
        // ArrayList<QuakeEntry> list2  = parser.read(source2); 
        // System.out.println("read data for "+list2.size()+" quakes"); // should sort after 4 passes
        // sortByMagnitudeWithCheck(list2);
        // for (QuakeEntry qe: list2) { 
        //    System.out.println(qe);
        // } 
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
