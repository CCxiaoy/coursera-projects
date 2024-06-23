import edu.duke.*;
/**
 * 在这里给出对类 CaesarBreaker 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class CaesarBreaker {
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
    
    public String decrypt(String encrypted, int key) {
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26 - key);
        return message;
    }
    
    public void testDecrypt() {
        String orign = "String that is unencrypted";
        int key = 7;
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(orign, key);
        String messageDecrypted = decrypt(encrypted, key);
        System.out.println("message before Decrypted: \t" + encrypted);
        System.out.println("messageDecrypted: \t" + messageDecrypted);
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for(; start < message.length(); start+=2) {
            sb.append(message.charAt(start));
        }
        return sb.toString();
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
    
    // public String decryptTwoKeys(String encrypted) {
    public String decryptTwoKeys() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        
        // String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"; // last punctuation will lost, not sure if letter will lose?
        
        String firstHalfString = halfOfString(encrypted, 0);
        String secondHalfString = halfOfString(encrypted, 1);
        int firstKey = getKey(firstHalfString);
        int secondKey = getKey(secondHalfString);
        System.out.println("First key is: \t" + firstKey + ", Second key is: \t" + secondKey);
        
        String DecryptedFirstHalfString = decrypt(firstHalfString, firstKey);
        String DecryptedSecondHalfString = decrypt(secondHalfString, secondKey);
        
        StringBuilder decrypted = new StringBuilder();
        int resLength = DecryptedFirstHalfString.length() + DecryptedSecondHalfString.length() - 1;
        int i = 0, j = 0;
        while(i + j < resLength) {
            if((i + j) % 2 == 0) {
                decrypted.append(DecryptedFirstHalfString.charAt(i));
                i++;
            } else {
                decrypted.append(DecryptedSecondHalfString.charAt(j));
                j++;
            }
        }
        
        System.out.println(decrypted.toString());
        return "";
        // return decrypted.toString();
    }
}
