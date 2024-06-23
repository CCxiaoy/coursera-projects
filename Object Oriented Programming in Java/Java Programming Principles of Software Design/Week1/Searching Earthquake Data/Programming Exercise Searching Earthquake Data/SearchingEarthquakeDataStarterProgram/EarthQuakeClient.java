import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            double magnitude = qe.getMagnitude();
            if(magnitude > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            double dist = qe.getLocation().distanceTo(from)/1000.0; // meters => kilometers
            if(dist < distMax) {
                answer.add(qe);
                System.out.println(dist + " " + qe.getInfo());
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        // String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        // TODO
        ArrayList<QuakeEntry> bigQuakes = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe : bigQuakes) {
            System.out.println(qe);
        }
        System.out.println("Found " + bigQuakes.size() + "quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location cityNC = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location cityCA =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> answerNC = filterByDistanceFrom(list, 1000, cityNC); // unit: meters
        if(answerNC.size() > 0) {
            System.out.println("Found " + answerNC.size() +" quakes that match that criteria");
        }
        ArrayList<QuakeEntry> answerCA = filterByDistanceFrom(list, 1000, cityCA); // unit: meters
        if(answerCA.size() > 0) {
            System.out.println("Found " + answerCA.size() +" quakes that match that criteria");
    }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth,
    double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            double depth = qe.getDepth();
            if((minDepth < depth) && (depth < maxDepth)) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        System.out.println("Find quakes with depth between " + minDepth + " + and + " + maxDepth);
        ArrayList<QuakeEntry> answer = filterByDepth(list, minDepth, maxDepth);
        for(QuakeEntry qe : answer) {
            System.out.println(qe);
        }
        System.out.println("Found " + answer.size() +" quakes that match that criteria");
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where, // one of three values: (“start”, ”end”, or “any”)
    String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            String title = qe.getInfo();
            int index = title.indexOf(phrase);
            switch(where) {
                case "start":
                    if(index == 0) {
                        answer.add(qe);
                    }
                    break;
                case "end":
                    if(index == (title.length()-phrase.length())) {
                        answer.add(qe);
                    }
                    break;
                case "any":
                    if(index != -1) {
                        answer.add(qe);
                    }
                    break;
                // default:
            }
        }
        return answer;
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> answerCalifornia = filterByPhrase(list, "any", "Can");
        for(QuakeEntry qe : answerCalifornia) {
            System.out.println(qe);
        }
        System.out.println("Found " + answerCalifornia.size() +" quakes that match that criteria");
        
        // System.out.println("read data for "+list.size()+" quakes");
        // ArrayList<QuakeEntry> answerCan = filterByPhrase(list, "any", "Can");
        // for(QuakeEntry qe : answerCan) {
        //    System.out.println(qe);
        // }
        // System.out.println("Found " + answerCan.size() +" quakes that match that criteria");
        
        // System.out.println("read data for "+list.size()+" quakes");
        // ArrayList<QuakeEntry> answerExplosion = filterByPhrase(list, "start", "Explosion");
        // for(QuakeEntry qe : answerExplosion) {
        //    System.out.println(qe);
        // }
        // System.out.println("Found " + answerExplosion.size() +" quakes that match that criteria");
    }
}
