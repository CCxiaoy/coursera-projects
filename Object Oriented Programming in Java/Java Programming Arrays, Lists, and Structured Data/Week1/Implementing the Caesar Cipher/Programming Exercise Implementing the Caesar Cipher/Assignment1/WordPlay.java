import edu.duke.*;

/**
 * 在这里给出对类 WordPlay 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        boolean result = false;
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        for(int i = 0; i < vowels.length; i++) {
            if(Character.toLowerCase(ch) == vowels[i]) {
                result = true;
            }
        }
        return result;
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder result = new StringBuilder(phrase);
        for(int i = 0; i < phrase.length(); i++) {
            char currChar = result.charAt(i);
            if(isVowel(currChar)) {
                result.setCharAt(i, ch);
            }
        }
        return result.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder result = new StringBuilder(phrase);
        for(int i = 0; i < phrase.length(); i++) {
            if(Character.toLowerCase(result.charAt(i)) == Character.toLowerCase(ch)) {
                if(i % 2 == 0) {
                    // odd location
                    result.setCharAt(i, '*');
                } else {
                    // even location
                    result.setCharAt(i, '+');
                }
            }
        }
        return result.toString();
    }
    
    public void isVowelTester() {
        boolean res1 = isVowel('a');
        System.out.println("The result of " + "a" + " is " + res1);
        boolean res2 = isVowel('e');
        System.out.println("The result of " + "e" + " is " + res2);
        boolean res3 = isVowel('b');
        System.out.println("The result of " + "b" + " is " + res3);
        boolean res4 = isVowel(' ');
        System.out.println("The result of " + " " + " is " + res4);
        boolean res5 = isVowel('*');
        System.out.println("The result of " + "*" + " is " + res5);
    }
    
    public void replaceVowelsTester() {
        String res1 = replaceVowels("Hello World", '*');
        System.out.println(res1);
    }
    
    public void emphasizeTester() {
        String res1 = emphasize("dna ctgaaactga", 'a');
        System.out.println(res1);
        String res2 = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println(res2);
    }
}
