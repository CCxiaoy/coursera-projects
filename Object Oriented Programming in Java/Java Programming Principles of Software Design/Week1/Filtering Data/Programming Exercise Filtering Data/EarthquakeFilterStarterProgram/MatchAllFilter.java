import java.util.*;
import edu.duke.*;

/**
 * 在这里给出对类 MatchAllFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;
    
    public MatchAllFilter() {
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f) {
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for(Filter f : filters) {
            if(!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        StringBuilder sb = new StringBuilder();
        for(Filter f : filters) {
            sb.append(f.getName());
        }
        return sb.toString();
    }
}
