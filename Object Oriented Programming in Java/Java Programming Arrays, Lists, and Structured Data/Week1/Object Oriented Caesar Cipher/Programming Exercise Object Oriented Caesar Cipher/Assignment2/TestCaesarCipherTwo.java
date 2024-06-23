import edu.duke.*;
/**
 * 在这里给出对类 TestCaesarCipherTwo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for(; start < message.length(); start+=2) {
            sb.append(message.charAt(start));
        }
        return sb.toString();
    }
    
    public int[] countLetters(String s) {
        int [] countors = new int [26];
        String alphat = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            int position = alphat.indexOf(Character.toLowerCase(current));
            if(position != -1) {
                countors[position]++;
            }
        }
        return countors;
    }
    
    public int maxIndex(int [] values) {
        int largestIndex = 0;
        for(int i = 0; i < values.length; i++) {
            if(values[i] > values[largestIndex]) {
                largestIndex = i;
            }
        }
        return largestIndex;
    }
    
    public int getKey(String s) {
        int [] countors = countLetters(s);
        int encryptedELocation = maxIndex(countors);
        
        int ePosition = 4; // (abcde)
        int key = encryptedELocation - ePosition;
        if(encryptedELocation < ePosition) {
            key = 26 + encryptedELocation - ePosition;
        }
        
        return key;
    }
    
    public String breakCaesarCipher(String input) {
        String input1 = halfOfString(input, 0);
        String input2 = halfOfString(input, 1);
        int key1 = getKey(input1);
        int key2 = getKey(input2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String decrypted = cc.decrypt(input);
        return decrypted;
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        String encrypted = cc.encrypt(input);
        System.out.println("Encrypted: \t" + encrypted);
        
        String decrypted1 = cc.decrypt(encrypted);
        System.out.println("Decrypted with key: \t" + decrypted1);
        
        String decrypted2 = breakCaesarCipher(encrypted);
        System.out.println("Decrypted without key: \t" + decrypted2);
    }
}
