
/**
 * 在这里给出对类 MinutesFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class MinutesFilter implements Filter {
    private int minMinutes;
    private int maxMinutes;
    
    public MinutesFilter(int minMinutes, int maxMinutes) {
        this.minMinutes = minMinutes;
        this.maxMinutes = maxMinutes;
    }
    
    public boolean satisfies(String id) {
        int movieMinutes = MovieDatabase.getMinutes(id);
        if(minMinutes <= movieMinutes && movieMinutes <= maxMinutes) {
            return true;
        }
        return false;
    }
}
