
/**
 * 在这里给出对类 EfficientMarkovWord 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private HashMap<WordGram, ArrayList<String>> myMap;
    private Random myRandom;
    private int myOrder;
        
    public EfficientMarkovWord(int order) {
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
                if(tempIndex >= words.length) {
                    flag = false;
                    break;
                }
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
    
    private String substring(int start, int end) { // similar to String.substring
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < end; i++) {
            sb.append(myText[i]);
        }
        return sb.toString();
    }
    
    private void buildMap() {
        // ToDo debug arraylists content same
        myMap = new HashMap<WordGram, ArrayList<String>>();
        for(int i = 0; i <= myText.length-myOrder; i++) {
            WordGram target = new WordGram(myText, i, myOrder); // target // key
            boolean exits = myMap.containsKey(target);
            if(exits) {
                continue;
            }
            ArrayList<String> follow = new ArrayList<String>();
            myMap.put(target, follow);
            int start = 0;
            while(start < myText.length-myOrder) {
                int keyPos = indexOf(myText, target, start);
                if(keyPos == -1 || keyPos == (myText.length-myOrder)) {
                    break;
                }
                follow.add(myText[keyPos+myOrder]);
                start=keyPos+1;
            }
        }
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        if(myMap.containsKey(kGram)) {
            return myMap.get(kGram);
        }
        return new ArrayList<String>();
    }

    public String getRandomText(int numWords){
        buildMap();
        printHashMapInfo();
        
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
    
    public void printHashMapInfo() {
        // Print the HashMap // Only do this if the HashMap is small
        // for(WordGram key : myMap.keySet()) {
        //    System.out.println("Key: " + key + ", Value: " + myMap.get(key));
        // }
        // Print the number of keys in the HashMap
        System.out.println("The number of keys in the HashMap: " + myMap.size());
        // Print the size of the largest value in the HashMap
        int largestArrayListSize = 0;
        for(WordGram key : myMap.keySet()) {
            if(myMap.get(key).size() > largestArrayListSize) {
                largestArrayListSize = myMap.get(key).size();
            }
        }
        System.out.println("The size of the largest value: " + largestArrayListSize);
        // Print the keys that have the maximum size value.
        ArrayList<WordGram> keysWithLargestSize = new ArrayList<WordGram>();
        for(WordGram key : myMap.keySet()) {
            if(myMap.get(key).size() == largestArrayListSize) {
                keysWithLargestSize.add(key);
            }
        }
        System.out.println(keysWithLargestSize);
    }
}
