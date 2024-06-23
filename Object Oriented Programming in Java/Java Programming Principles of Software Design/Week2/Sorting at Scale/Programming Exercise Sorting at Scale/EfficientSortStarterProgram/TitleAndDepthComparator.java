import java.util.*;

/**
 * 在这里给出对类 TitleAndDepthComparator 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int compareTitleRes = q1.getInfo().compareTo(q2.getInfo());
        if(compareTitleRes != 0) {
            return compareTitleRes;
        } else {
            int compareDepthRes = Double.compare(q1.getDepth(), q2.getDepth());
            return compareDepthRes;
        }
    }
}
