import edu.duke.*;
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currentIndex = dna.indexOf(stopCodon, startIndex+3);
        while(currentIndex < dna.length()) {
            if(currentIndex == -1) {
                return dna.length();
            }
            if((currentIndex - startIndex) % 3 == 0) {
                return currentIndex;
            }
            currentIndex = dna.indexOf(stopCodon, currentIndex+1);
        }
        return dna.length();
    }
      
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1) {
            return "";
        }
        int indexTAA = findStopCodon(dna, startIndex, "TAA");
        int indexTAG = findStopCodon(dna, startIndex, "TAG");
        int indexTGA = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(indexTAA, Math.min(indexTAG, indexTGA));
        if(minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }
    
        
    public void printAllGenes(String dna) {
        int startPoint = 0;
        while(true) {
            String gene = findGene(dna.substring(startPoint, dna.length()));
            if(gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            startPoint = dna.indexOf(gene, startPoint) + gene.length();
        }
    }    
    
    public void testFindStopCodon() {
        int res1 = findStopCodon("TGA", 0, "TAA");
        if(res1 == 3) {
            System.out.println("1. success");
        }
        int res2 = findStopCodon("TAAGTGAAATGA", 0, "TGA");
        if(res2 == 9) {
            System.out.println("2. success");
        }
        int res3 = findStopCodon("TAAGTGAAATGA", 1, "TGA");
        if(res3 == 4) {
            System.out.println("3. success");
        }
    }
    
    public void testFindGene() {
        // String dna1 = "ATGTAATAA";
        String dna1 = "AATGCTAACTAGCTGACTAAT";
        System.out.println("dna: " + dna1);
        System.out.println(findGene(dna1));
        String dna2 = "ATGATAAAATAGTAA";
        System.out.println("dna: " + dna2);
        System.out.println(findGene(dna2));
        String dna3 = "TAATAG";
        System.out.println("dna: " + dna3);
        System.out.println(findGene(dna3));
        String dna4 = "ATGATGATG";
        System.out.println("dna: " + dna4);
        System.out.println(findGene(dna4));
        String dna5 = "ATGCGCTAA"; 
        System.out.println("dna: " + dna5);
        System.out.println(findGene(dna5));
    }
}


