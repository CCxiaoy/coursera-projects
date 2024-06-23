
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        if(quakeData.size() < howMany) {
            howMany = quakeData.size();
        }
        int countor = 0;
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        while(countor < howMany) {
            double closestDist = copy.get(0).getLocation().distanceTo(current)/1000; // meters => kilometers
            QuakeEntry closestQE = null;
            int currentRemoveI = 0;
            for(int i = 0; i < copy.size(); i++) {
                QuakeEntry currentQE = copy.get(i);
                double currentDist = currentQE.getLocation().distanceTo(current)/1000;
                if(currentDist < closestDist) {
                    closestQE = currentQE;
                    closestDist = currentDist;
                    currentRemoveI = i;
                }
            }
            ret.add(closestQE);
            copy.remove(currentRemoveI);
            countor++;
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "data/nov20quakedata.atom";
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3); // city jarkata
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
