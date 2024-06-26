
/**
 * 在这里给出对类 DirectorsFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class DirectorsFilter implements Filter {
    private String directors;
    
    public DirectorsFilter(String directors) { // Example: "Charles Chaplin,Michael Mann,Spike Jonze"
        this.directors = directors;
    }
    
    public boolean satisfies(String id) {
        String [] movieDirecotrs = MovieDatabase.getDirector(id).split(",");
        for(String movieDirector : movieDirecotrs) {
            if(directors.indexOf(movieDirector) != -1) {
                return true;
            }
        }
        return false;
    }
}
