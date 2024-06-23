
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            
            // System.out.println("Key: " + key + "Follows: " + follows);
            
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length) {
            int keyPos = indexOf(myText, key, pos);
            if(keyPos == -1) {
                break;
            }
            if(keyPos == (myText.length-1)) {
                break;
            }
            String followWord = myText[keyPos+1];
            follows.add(followWord);
            pos = keyPos+1;
        }
        return follows;
    }
    
    private int indexOf(String[] words, String target, int start) {
        for(int i = start; i < words.length; i++) {
            String curWord = words[i];
            if(curWord.equals(target)) {
                return i;
            }
        }
        return -1; // if not find
    }

    public void testIndexOf() { // Method only used for testing the indexOf method
        String[] testWords = "this is just a test yes this is a simple test".split("\\s+");
        
        System.out.println(indexOf(testWords, "this", 0));
        System.out.println(indexOf(testWords, "this", 3));
        
        System.out.println(indexOf(testWords, "frog", 0));
        System.out.println(indexOf(testWords, "frog", 5));
        
        System.out.println(indexOf(testWords, "simple", 2));
        System.out.println(indexOf(testWords, "test", 5));
    }
    
}
