
/**
 * 在这里给出对类 DistanceFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class DistanceFilter implements Filter
{
    private Location location;
    private double disMax;
    
    public DistanceFilter(Location loc, double max) {
        location = loc;
        disMax = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        double curDis = location.distanceTo(qe.getLocation());
        return curDis < disMax;
    }
    
    public String getName() {
        return "Distance";
    }
}
