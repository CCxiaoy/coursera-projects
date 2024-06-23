import edu.duke.*;
/**
 * 在这里给出对类 CaesarCipher 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class CaesarCipher {
    private int mainKey;
    private String alphabet;
    private String shiftedAlphabet;
    
    public CaesarCipher(int key) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder();
        for(int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            char currentLow = Character.toLowerCase(current);
            int index = alphabet.indexOf(currentLow);
            char rep;
            if(index != -1) {
                if(current == currentLow) {
                    rep = shiftedAlphabet.charAt(index);   
                } else {
                    rep = Character.toUpperCase(shiftedAlphabet.charAt(index));
                }         
            } else {
                rep = current;
            }
            encrypted.append(rep);
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
