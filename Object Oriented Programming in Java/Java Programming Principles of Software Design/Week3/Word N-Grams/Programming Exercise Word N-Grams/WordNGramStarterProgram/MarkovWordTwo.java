
/**
 * 在这里给出对类 MarkovWordTwo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            
            // System.out.println("Key: " + key + "Follows: " + follows);
            
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length) {
            int keyPos = indexOf(myText, key1, key2, pos);
            if(keyPos == -1) {
                break;
            }
            if(keyPos == (myText.length-2)) {
                break;
            }
            String followWord = myText[keyPos+2];
            follows.add(followWord);
            pos = keyPos+2;
        }
        return follows;
    }
    
    private int indexOf(String[] words, String target1, String target2, int start) {
        for(int i = start; i < words.length-1; i++) {
            String curWord1 = words[i];
            String curWord2 = words[i+1];
            if(curWord1.equals(target1) && curWord2.equals(target2)) {
                return i;
            }
        }
        return -1; // if not find
    }
    
}
