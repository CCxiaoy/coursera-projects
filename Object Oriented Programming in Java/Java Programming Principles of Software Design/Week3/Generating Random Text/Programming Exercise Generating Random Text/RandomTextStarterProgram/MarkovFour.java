
/**
 * 在这里给出对类 MarkovFour 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovFour {
    private String myText;
    private Random myRandom;
    
    public MarkovFour() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length()) {
            int index = myText.indexOf(key, pos);
            if(index == -1 || index == myText.length()-key.length()) {
                break;
            }
            follows.add(myText.substring(index + key.length(), index + key.length() + 1));
            pos=index+1;
        }
        return follows;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-4);
        sb.append(myText.substring(index, index+4));
        for(int k=0; k < numChars-4; k++){
            ArrayList<String> follows = getFollows(sb.substring(k, k+4));
            index = myRandom.nextInt(follows.size());
            sb.append(follows.get(index));
        }
        
        return sb.toString();
    }
}
