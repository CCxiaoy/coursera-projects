import edu.duke.*;
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int countor = 0;
        int startIndex = 0;
        while(true) {
            int stringaIndex = stringb.indexOf(stringa, startIndex);
            if(stringaIndex == -1 ) {
                break;
            }
            countor++;
            startIndex = stringaIndex + stringa.length(); 
        }
        return countor;
    }
    
    public void testHowMany() {
        int res1 = howMany("GAA", "ATGAACGAATTGAATC");
        if(res1 == 3) {
            System.out.println("1. success!");
        }
        int res2 = howMany("AA", "ATAAAA");
        if(res2 == 2) {
            System.out.println("2. success!");
        }
    }
}
