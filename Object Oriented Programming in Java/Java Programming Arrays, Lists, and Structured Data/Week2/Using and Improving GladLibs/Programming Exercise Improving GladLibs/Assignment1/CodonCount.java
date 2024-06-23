import edu.duke.*;
import java.util.*;

/**
 * 在这里给出对类 CodonCount 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class CodonCount {
    private HashMap<String, Integer> codonMap;
    
    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna) { // start: 0, 1, 2
        codonMap.clear(); // make sure map empty before any operations
        for(int i = start; i+2 < dna.length(); i+=3) {
            String newCodon = dna.substring(i, i+3);
            if(!codonMap.containsKey(newCodon)) {
                codonMap.put(newCodon, 1);
            } else {
                codonMap.put(newCodon, codonMap.get(newCodon)+1);
            }
        }
    }
    
    public int getUniqueCondonNum() {
        int count = codonMap.size();
        return count;
    }
    
    public String getMostCommonCodon() {
        int maxCount = 0;
        String maxCodon = "";
        for(String codon : codonMap.keySet()) {
            if(codonMap.get(codon) > maxCount) {
                maxCount = codonMap.get(codon);
                maxCodon = codon;
            }
        }
        return maxCodon;
    }
    
    public void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
        for(String codon : codonMap.keySet()) {
            int count = codonMap.get(codon);
            if((start <= count) && (count <= end)) {
                System.out.println(codon + " " + count);
            }
        }
    }
    
    public void codonCountTester() {
        FileResource fr = new FileResource();
        String input = fr.asString().trim().toUpperCase();
        
        for(int i = 0; i < 3; i++) {
            buildCodonMap(i, input);
            System.out.println("Reading frame starting with " + i + " results in " + getUniqueCondonNum() + " unique codons");
            System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + codonMap.get(getMostCommonCodon()));
            printCodonCounts(7, 7);
        }        
    }
}
