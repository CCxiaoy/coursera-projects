
/**
 * 在这里给出对类 DepthFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class DepthFilter implements Filter
{
    private double depMin;
    private double depMax;
    
    public DepthFilter(double min, double max) {
        depMin = min;
        depMax = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        double curMag = qe.getDepth();
        return (
            depMin <= curMag && curMag <= depMax
        );
    }
    
    public String getName() {
        return "Depth";
    }
}
