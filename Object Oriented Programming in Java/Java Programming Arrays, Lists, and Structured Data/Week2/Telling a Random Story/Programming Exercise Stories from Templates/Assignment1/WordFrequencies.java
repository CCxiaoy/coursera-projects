import edu.duke.*;
import java.util.ArrayList;
/**
 * 在这里给出对类 WordFrequencies 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        
        FileResource fr = new FileResource();
        for(String s : fr.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if(index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq+1);
            }
        }
    }
    
    public int findIndexOfMax() {
        int maxFreq = myFreqs.get(0);
        int maxIndex = 0;
        for(int i = 0; i < myFreqs.size(); i++) {
            if(maxFreq < myFreqs.get(i)) {
                maxFreq = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void tester() {
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        for(int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + myWords.get(i));
        }
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: "+myWords.get(maxIndex)+" "+myFreqs.get(maxIndex));
    }
}
