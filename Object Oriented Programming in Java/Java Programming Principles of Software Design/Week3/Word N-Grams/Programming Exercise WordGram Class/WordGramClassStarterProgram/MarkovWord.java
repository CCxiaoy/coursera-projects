
/**
 * 在这里给出对类 MarkovWord 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        String tarFirstWord = target.wordAt(0);
        for(int i = start; i < words.length; i++) {
            String curWord = words[i];
            int tempIndex = i;
            boolean flag = true;
            for(int tarIndex = 0; tarIndex < target.length(); tarIndex++, tempIndex++) {
                if(!words[tempIndex].equals(target.wordAt(tarIndex))) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                return i;   
            }
        }
        return -1; // if not find
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {        
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length) {
            int keyPos = indexOf(myText, kGram, pos);
            if(keyPos == -1) {
                break;
            }
            if(keyPos == (myText.length-myOrder)) {
                break;
            }
            String followWord = myText[keyPos+myOrder];
            follows.add(followWord);
            pos = keyPos+1;
        }
        return follows;
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram.toString());
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
}
