import edu.duke.*;
import java.util.ArrayList;
/**
 * 在这里给出对类 CharactersInPlay 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class CharactersInPlay {
    private ArrayList<String> playNames;
    private ArrayList<Integer> playFreqs;
    
    public CharactersInPlay() {
        playNames = new ArrayList<String>();
        playFreqs = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        int index = playNames.indexOf(person); 
        if(index == -1) {
            playNames.add(person);
            playFreqs.add(1);
        } else {
            int freq = playFreqs.get(index);
            playFreqs.set(index, freq+1);
        }
    }
    
    public void findAllCharacters() {
        FileResource fr = new FileResource();
        for(String line : fr.lines()) {
            int periodIndex = line.indexOf(".");
            if(periodIndex != -1) {
                String name = line.substring(0, periodIndex);
                update(name);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for(int i = 0; i < playFreqs.size(); i++) {
            int freq = playFreqs.get(i);
            if((num1 <= freq) && (freq <= num2)) {
                System.out.println(playNames.get(i) + " " + playFreqs.get(i));
            }
        }
    }
    
    public void tester() {
        findAllCharacters();
        int threshold = 1;
        int max = 0;
        for(int i = 0; i < playFreqs.size(); i++) {
            if(playFreqs.get(i) > threshold) {
                System.out.println(playNames.get(i) + " " + playFreqs.get(i));
            }
            if(playFreqs.get(i) > playFreqs.get(max)) {
                max = i;
            }
        }
        System.out.println("Max: " + playNames.get(max) + " " + playFreqs.get(max));
        
        System.out.println("----------");
        
        charactersWithNumParts(10, 15);
    }
}
