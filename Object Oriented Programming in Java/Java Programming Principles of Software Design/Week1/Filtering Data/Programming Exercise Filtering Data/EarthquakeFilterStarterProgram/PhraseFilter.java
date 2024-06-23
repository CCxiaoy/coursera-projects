
/**
 * 在这里给出对类 PhraseFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class PhraseFilter implements Filter
{
    private String where; // "start", "end", or "any"
    private String phrase;
    
    public PhraseFilter(String where, String phrase) {
        this.where = where;
        this.phrase = phrase;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        int index = title.indexOf(phrase);
        switch(where) {
            case "start":
                if(index == 0) {
                    return true;
                }
                break;
            case "end":
                if(index == (title.length() - phrase.length())) {
                    return true;
                }
                break;
            case "any":
                if(index != -1) {
                    return true;
                }
                break;
        }
        return false;
    }
    
    public String getName() {
        return "Phrase";
    }
}
