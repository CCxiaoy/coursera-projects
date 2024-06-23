import edu.duke.*;
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String result = "";
        int startIndex = dna.toUpperCase().indexOf(startCodon); // startCodon start point
        if(startIndex == -1) { // Fail to find ATG
            return "";
        }
        int endIndex = dna.toUpperCase().indexOf(stopCodon, startIndex); // stopCodon start point
        if(endIndex == -1) { // Fail to find TTA
            return "";
        }
        if((endIndex - startIndex) % 3 != 0) { // Length not the multiples of 3
            return "";
        }
        result = dna.substring(startIndex, endIndex+3);
        return result;
    }
    
    public void testSimpleGene() {
        String dna1 = "ATGGGTTAAGTC";
        System.out.println("DNA String is = " + dna1);
        System.out.println("Gene = " + findSimpleGene(dna1, "ATG", "TAA")); // ATGGGTTAA
        
        String dna2 = "gatgctataat";
        System.out.println("DNA String is = " + dna2);
        System.out.println("Gene = " + findSimpleGene(dna2, "ATG", "TAA")); // atgctataa
    }
}
