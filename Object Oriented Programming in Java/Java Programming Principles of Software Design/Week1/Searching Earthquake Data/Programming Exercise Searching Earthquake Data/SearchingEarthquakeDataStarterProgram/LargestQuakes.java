import java.util.*;

/**
 * 在这里给出对类 LargestQuakes 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        // String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // for(QuakeEntry qe : list) {
        //    System.out.println(qe);
        // }
        System.out.println("read data for "+list.size()+" quakes");
        
        int largestIndex = indexOfLargest(list);
        System.out.println("Location: " + largestIndex + " Magnitude: " + list.get(largestIndex));
        
        ArrayList<QuakeEntry> largestQuakes = getLargest(list, 50);
        for(QuakeEntry qe : largestQuakes) {
            System.out.println(qe);
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int largestIndex = 0;
        double largestMag = 0;
        for(int i = 0; i < data.size(); i++) {
            double curMag = data.get(i).getMagnitude();
            if(curMag > largestMag) {
                largestMag = curMag;
                largestIndex = i;
            }
        }
        return largestIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if(quakeData.size() < howMany) {
            howMany = quakeData.size();
        }
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        int countor = 0;
        while(countor < howMany) {
            int largestIndex = indexOfLargest(copy);          
            answer.add(copy.get(largestIndex));
            copy.remove(largestIndex);
            countor++;
        }
        return answer;
    }
}
