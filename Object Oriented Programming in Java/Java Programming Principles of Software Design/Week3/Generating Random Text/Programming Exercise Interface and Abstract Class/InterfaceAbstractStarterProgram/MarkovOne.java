
/**
 * 在这里给出对类 MarkovOne 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        sb.append(myText.charAt(index));
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(sb.substring(k, k+1));
            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
        }
        
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order 1";
    }
}
