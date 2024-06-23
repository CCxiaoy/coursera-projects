import edu.duke.*;
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

public class Part1 {
    public String findSimpleGene(String dna) {
        String result = "";
        int startIndex = dna.indexOf("ATG"); // ATG start point
        if(startIndex == -1) { // Fail to find ATG
            return "";
        }
        int endIndex = dna.indexOf("TAA", startIndex); // TAA start point
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
        String dna1 = "ATCGCTTTA"; // DNA with no “ATG”
        System.out.println("DNA String is = " + dna1);
        System.out.println("Gene = " + findSimpleGene(dna1));
        
        String dna2 = "ATGCCCATC"; // DNA with no “TAA”
        System.out.println("DNA String is = " + dna2);
        System.out.println("Gene = " + findSimpleGene(dna2));
        
        String dna3 = "GGGCCCAAA"; // DNA with no “ATG” or “TAA”
        System.out.println("DNA String is = " + dna3);
        System.out.println("Gene = " + findSimpleGene(dna3));
        
        String dna4 = "GATGCCGTAAG"; // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene)
        System.out.println("DNA String is = " + dna4);
        System.out.println("Gene = " + findSimpleGene(dna4));
        
        String dna5 = "GATGCGTAAG"; // DNA with ATG, TAA and the substring between them is not a multiple of 3
        System.out.println("DNA String is = " + dna5);
        System.out.println("Gene = " + findSimpleGene(dna5));
    }
}
