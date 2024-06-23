import edu.duke.*;
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        boolean result = true;
        int firstIndex = stringb.indexOf(stringa);
        if(firstIndex == -1) {
            return false;
        }
        int secondIndex = stringb.indexOf(stringa, firstIndex+stringa.length());
        if(secondIndex == -1) {
            return false;
        }
        return result;
    }
    
    public String lastPart(String stringa, String stringb) {
        String result = stringb;
        int aIndex = stringb.indexOf(stringa);
        if(aIndex == -1) {
            return stringb;
        }
        result = stringb.substring(aIndex+stringa.length(), stringb.length());
        return result;
    }
    
    public void testing() {
        // Test method twoOccurrences
        String a1 = "by";
        String b1 = "A story by Abby Long";
        boolean result1 = twoOccurrences(a1, b1);
        System.out.println("String a is = " + a1 + "; String b is = " + b1);
        System.out.println("The result is = " + result1); // true
        String a2 = "atg";
        String b2 = "ctgtatgta";
        boolean result2 = twoOccurrences(a2, b2);
        System.out.println("String a is = " + a2 + "; String b is = " + b2);
        System.out.println("The result is = " + result2); // false
        
        // Test method lastPart
        String a3 = "an";
        String b3 = "banana";
        String result3 = lastPart(a3, b3);
        System.out.println("The part of the string after" + a3 + " in " + b3 + " is " + result3 + ".");
        String a4 = "zoo";
        String b4 = "forest";
        String result4 = lastPart(a4, b4);
        System.out.println("The part of the string after" + a4 + " in " + b4 + " is " + result4 + ".");
    }
}
