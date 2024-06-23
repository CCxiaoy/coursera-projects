import edu.duke.*;

/**
 * 在这里给出对类 CaesarCipher 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetLower = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetUpper = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0, key);
        String shiftedAlphabetUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0, key);
        for(int i = 0; i < encrypted.length(); i++) {
            char currentChar = encrypted.charAt(i);
            int idxLower = alphabetLower.indexOf(currentChar);
            if(idxLower != -1) {
                char newChar = shiftedAlphabetLower.charAt(idxLower);
                encrypted.setCharAt(i, newChar);
            } else {
                int idxUpper = alphabetUpper.indexOf(currentChar);
                if(idxUpper != -1) {
                    char newChar = shiftedAlphabetUpper.charAt(idxUpper);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetLower = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetUpper = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabetLower1 = alphabetLower.substring(key1) + alphabetLower.substring(0, key1);
        String shiftedAlphabetLower2 = alphabetLower.substring(key2) + alphabetLower.substring(0, key2);
        String shiftedAlphabetUpper1 = alphabetUpper.substring(key1) + alphabetUpper.substring(0, key1);
        String shiftedAlphabetUpper2 = alphabetUpper.substring(key2) + alphabetUpper.substring(0, key2);
        for(int i = 0; i < encrypted.length(); i++) {
            char currentChar = encrypted.charAt(i);
            String shiftedAlphabetLower = null;
            String shiftedAlphabetUpper = null;
            if(i % 2 == 0) {
                shiftedAlphabetLower = shiftedAlphabetLower1;
                shiftedAlphabetUpper = shiftedAlphabetUpper1;
            } else {
                shiftedAlphabetLower = shiftedAlphabetLower2;
                shiftedAlphabetUpper = shiftedAlphabetUpper2;
            }
            int idxLower = alphabetLower.indexOf(currentChar);                   
            if(idxLower != -1) {
                char newChar = shiftedAlphabetLower.charAt(idxLower);
                encrypted.setCharAt(i, newChar);
            } else {
                int idxUpper = alphabetUpper.indexOf(currentChar);
                if(idxUpper != -1) {
                    char newChar = shiftedAlphabetUpper.charAt(idxUpper);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
    public void testCaesar() {
        // int key = 23;
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        // String encrypted = encrypt(message, key);
        // System.out.println("key is " + key + "\n" + encrypted); 
        
        // String encrypted1 = encrypt("First Legion", 23);
        // System.out.println("key is " + "First Legion" + "\n" + encrypted1); // Cfopq Ibdflk
        
        // String encrypted2 = encrypt("First Legion", 17);
        // System.out.println("key is " + "First Legion" + "\n" + encrypted2); // Wzijk Cvxzfe”
        
        String str3 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted3 = encrypt(str3, 15);
        System.out.println("key is " + str3 + "\n" + encrypted3); // Wzijk Cvxzfe”
    }
    
    public void testCaesarAdvanced() {
        // int key = 23;
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        // String encrypted = encrypt(message, key);
        // System.out.println("key is " + key + "\n" + encrypted); 
        
        // String encrypted1 = encryptTwoKeys("First Legion", 23, 17);
        // System.out.println("key is " + "First Legion" + "\n" + encrypted1); // Czojq Ivdzle
        
        String str2 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted2 = encryptTwoKeys(str2, 21, 8);
        System.out.println("key is " + str2 + "\n" + encrypted2); // Czojq Ivdzle
    }
}
