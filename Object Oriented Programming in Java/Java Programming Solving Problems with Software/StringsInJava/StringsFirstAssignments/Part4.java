import edu.duke.*;
/**
 * 在这里给出对类 Part4 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part4 {
    public void findYoutube() {
        URLResource fr = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String line : fr.lines()) {
            int indexYoutube = line.toLowerCase().indexOf("youtube.com");
            if(indexYoutube > -1) {
                int indexLeftQuotation = line.lastIndexOf("\"", indexYoutube);
                int indexRightQuotation = line.indexOf("\"", indexYoutube);
                if (indexLeftQuotation != -1 && indexLeftQuotation != -1) {
                    System.out.println(line.substring(indexLeftQuotation+1, indexRightQuotation));
                }
            }
        }
    }
}
