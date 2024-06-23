
/**
 * 在这里给出对类 CaesarCipherTwo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class CaesarCipherTwo {
    private int mainKey1;
    private int mainKey2;
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    private String[] apartStringToTwo(String input) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i = 0; i < input.length(); i++) {
            if(i % 2 == 0) {
                sb1.append(input.charAt(i));
            } else {
                sb2.append(input.charAt(i));
            }
        }
        String input1 = sb1.toString();
        String input2 = sb2.toString();
        String [] strArr = {input1, input2};
        return strArr;
    }
    
    private String encryptSingle(String input, String shiftedAlphabet) {
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
    
    private String mergeTwoString(String input1, String input2) {
        StringBuilder sb = new StringBuilder();
        int l1 = input1.length(), l2 = input2.length();
        int totalLength = l1 + l2 -1;
        int i1 = 0, i2 = 0;
        while((i1 + i2) < totalLength) {
            if((i1 + i2) % 2 == 0) {
                sb.append(input1.charAt(i1));
                i1++;
            } else {
                sb.append(input2.charAt(i2));
                i2++;
            }
        }
        String merged = sb.toString();
        return merged;
    }
    
    public String encrypt(String input) {
        String [] inputArr = apartStringToTwo(input);
        String input1 = inputArr[0], input2 = inputArr[1];
        String encrypted1 = encryptSingle(input1, shiftedAlphabet1);
        String encrypted2 = encryptSingle(input2, shiftedAlphabet2);
        String encrypted = mergeTwoString(encrypted1, encrypted2);
        return encrypted;
    }
    
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo((26 - mainKey1), (26 - mainKey2));
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
