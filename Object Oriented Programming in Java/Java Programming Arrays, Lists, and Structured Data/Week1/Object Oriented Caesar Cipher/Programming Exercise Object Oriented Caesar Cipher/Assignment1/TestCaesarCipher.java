import edu.duke.*;
/**
 * 在这里给出对类 TestCaesarCipher 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class TestCaesarCipher {
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
    
    public String breakCaesarCipher(String input) {
        int key; // encrypt key
        int [] countors = countLetters(input);
        int encryptedELocation = maxIndex(countors);
        
        int ePosition = 4; // (abcde)
        key = encryptedELocation - ePosition;
        if(encryptedELocation < ePosition) {
            key = 26 + encryptedELocation - ePosition;
        }
        
        CaesarCipher cc = new CaesarCipher(key);
        String decrypted = cc.decrypt(input); // use encrypt to decrypt
        return decrypted;
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String str = fr.asString();
        
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(str);
        System.out.println("Encrypted String: \t" + encrypted);
        
        String decryptedWith = cc.decrypt(encrypted);
        System.out.println("Decrypted String with key: \t" + decryptedWith);
        
        String decryptedWithout = breakCaesarCipher(encrypted);
        System.out.println("Decrypted String without key: \t" + decryptedWithout);
    }
}
