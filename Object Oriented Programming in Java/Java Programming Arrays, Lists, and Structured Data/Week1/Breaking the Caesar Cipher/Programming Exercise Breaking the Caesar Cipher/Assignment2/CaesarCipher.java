import edu.duke.*;

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
    
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
}

