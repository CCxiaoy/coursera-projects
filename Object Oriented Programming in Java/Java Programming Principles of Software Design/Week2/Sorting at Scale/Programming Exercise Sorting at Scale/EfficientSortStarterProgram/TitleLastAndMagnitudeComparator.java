import java.util.*;

/**
 * 在这里给出对类 TitleLastAndMagnitudeComparator 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] words1 = q1.getInfo().split(" ");
        String lastWord1 = words1[words1.length - 1];
        String[] words2 = q2.getInfo().split(" ");
        String lastWord2 = words2[words2.length - 1];
        int compareLastWordRes = lastWord1.compareTo(lastWord2);
        if(compareLastWordRes != 0) {
            return compareLastWordRes;
        } else {
            int compareMagnitudeRes = new MagnitudeComparator().compare(q1, q2);
            return compareMagnitudeRes;
        }
    }
}
