import java.util.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices)
    {
        StringBuilder sb = new StringBuilder();
        
        for (int i = whichSlice; i < message.length(); i += totalSlices)
        {
            sb.append(message.charAt(i));
        }
        
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        for (int i = 0; i < klength; i++)
        {
            String message = sliceString(encrypted, i, klength);
            
            CaesarCracker cc = new CaesarCracker();
            
            int k = cc.getKey(message);
            
            key[i] = k;
        }
        
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> words = new HashSet<String>();
        
        for (String word : fr.lines())
        {
            word = word.toLowerCase();
            
            if (!words.contains(word))
            {
                words.add(word);
            }
        }
        
        return words;
    }
    
    public int countWords(String message, HashSet<String> words)
    {
        
        String[] messageWords = message.split("\\W+");
        
        int counter = 0;
        
        for (int i = 0; i < messageWords.length; i++)
        {
            String word = messageWords[i].toLowerCase();
            
            if (words.contains(word))
            {
                counter++;
            }
        }
        
        return counter;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> words) {
        
        char mostCommenLetter = mostCommonCharIn(words);
        int max = 0;
        int[] key = null;
        VigenereCipher vc = null;
        String decryptedMessage = "";
        
        for (int i = 1; i <= 100; i++)
        {
            int[] currentKey = tryKeyLength(encrypted, i, mostCommenLetter);
            
            vc = new VigenereCipher(currentKey);
            
            decryptedMessage =  vc.decrypt(encrypted);
            
            int count = countWords(decryptedMessage, words);
            
            if (count > max)
            {
                max = count;
                key = currentKey;
            }
        }
        
        if (key != null)
        {
            vc = new VigenereCipher(key);
            decryptedMessage =  vc.decrypt(encrypted);
        }
        System.out.print("key is: ");
        for (int i = 0; i < key.length; i++)
        {
            System.out.print(key[i] + " ");
        }
        System.out.println("");
        System.out.println("key length is: " + key.length);
        
        System.out.println("This file contains " + max + " valid words out of " + encrypted.split("\\W+").length);
        return decryptedMessage;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charFreq = new HashMap<Character, Integer>();
        for(String word : dictionary) {
            for(int i = 0; i < word.length(); i++) {
                char curChar = word.charAt(i);
                if(!charFreq.containsKey(curChar)) {
                    charFreq.put(curChar, 1);
                } else {
                    int curCharFreq = charFreq.get(curChar);
                    charFreq.put(curChar, curCharFreq+1);
                }
            }
        }
        
        char mostFreqChar = '0';
        int maxFreq = 0;
        for(char c : charFreq.keySet()) {
            int curCharFreq = charFreq.get(c);
            if(curCharFreq > maxFreq) {
                maxFreq = curCharFreq;
                mostFreqChar = c;
            }
        }
        return mostFreqChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        String decrypted = null;
        String decryptedLanguage = null;
        int bestCountor = 0;
        for(String language : languages.keySet()) {
            HashSet<String> languageWords = languages.get(language);
            String curLanguageDecrypted = breakForLanguage(encrypted, languageWords);
            int curLanguageCountor = countWords(curLanguageDecrypted, languageWords);
            
            if(curLanguageCountor > bestCountor) {
                bestCountor = curLanguageCountor;
                decrypted = curLanguageDecrypted;
                decryptedLanguage = language;
            }
        }
        System.out.println("Language: " + decryptedLanguage + " Valid words: " + bestCountor);
        System.out.println(decrypted);
    }
    
    public void breakVigenere () {
        System.out.println("Start reading encrypted message");
        FileResource fr = new FileResource();        
        String encrypted = fr.asString();
        
        
        HashMap<String, HashSet<String>> languages  = new HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        
        System.out.println("Start reading languages dictionary");
        for(File f : dr.selectedFiles())  {
            FileResource dicFr = new FileResource(f);
            String fileName = f.getName();
            HashSet<String> words = readDictionary(dicFr);
            languages.put(fileName, words);
            System.out.println("Progress: Reading " + fileName + "Dictionary");
        }
        
        breakForAllLangs(encrypted, languages);
        
        // System.out.println("decrypted Message: ");
        // String[] result = decryptedMessage.split(System.lineSeparator(), 2);
        // System.out.println(result[0]);
        // System.out.println(decryptedMessage);
    }
}