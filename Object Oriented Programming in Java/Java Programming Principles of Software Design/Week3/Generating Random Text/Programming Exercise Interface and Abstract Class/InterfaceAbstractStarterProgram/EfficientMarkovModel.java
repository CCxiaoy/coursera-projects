
/**
 * 在这里给出对类 EfficientMarkovModel 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int numCharBased;
    private HashMap<String, ArrayList<String>> hm;
    
    public EfficientMarkovModel(int nNum) {
        numCharBased = nNum;
        myRandom = new Random();
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap(); // Initialize dictionary
        // System.out.println(hm);
        printHashMapInfo();
    }
    
    public void buildMap() {
        hm = new HashMap<String, ArrayList<String>>();
        int endPoint = myText.length() - numCharBased;
        for(int i = 0; i <= endPoint; i++) {
            String key = myText.substring(i, i+numCharBased);
            
            boolean exits = hm.containsKey(key);
            if(exits) {
                continue;
            }
            
            ArrayList<String> value = new ArrayList<String>();
            hm.put(key, value);
            int pos = 0;
            while(pos < endPoint) {
                int curPos = myText.indexOf(key, pos);
                if(curPos == -1 || curPos == endPoint) {
                    break;
                }
                value.add(myText.substring(curPos+numCharBased, curPos+numCharBased+1));
                pos = curPos+1;
            }
        }
    }
    
    protected ArrayList<String> getFollows(String key) {
        if(hm.containsKey(key)) {
            return hm.get(key);
        }
        return new ArrayList<String>();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-numCharBased);
        sb.append(myText.substring(index, index+numCharBased));
        for(int k=0; k < numChars-numCharBased; k++){
            // System.out.println("Available: " + sb.length());
            // System.out.println(k + " , " + numCharBased);
            // System.out.println(sb.substring(k, k+numCharBased));
            ArrayList<String> follows = getFollows(sb.substring(k, k+numCharBased));
            if(follows.size() > 0) {
                index = myRandom.nextInt(follows.size());
                sb.append(follows.get(index));
            } else {
                break;
            }
        }
        
        return sb.toString();
    }
    
    public void printHashMapInfo() {
        ArrayList<String> largestKey = new ArrayList<String>();
        int largestALSize = 0;
        for(String curKey : hm.keySet()) {
            int curSize = hm.get(curKey).size();
            if(curSize > largestALSize) {
                largestALSize = curSize;
                largestKey = new ArrayList<String>();
                largestKey.add(curKey);
            } else if(curSize == largestALSize) {
                largestKey.add(curKey);
            }
            // System.out.println("Key: " + curKey + ", Value: " + hm.get(curKey));
        }
        
        System.out.println("The number of keys in the HashMap: " + hm.size());
        System.out.println("The size of the largest ArrayList: " + largestALSize);
        System.out.println("The keys that have the maximum size value: " + largestKey);
    }
    
    public String toString() {
        return "EfficientMarkovModel of order " + numCharBased;
    }
}
