
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part3 {
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
    
    public int countGenes(String dna) {
        int countor = 0;
        int startPoint = 0;
        while(true) {
            String gene = findGene(dna.substring(startPoint, dna.length()));
            if(gene.isEmpty()) {
                break;
            }
            countor++;
            startPoint = dna.indexOf(gene, startPoint) + gene.length();
        }
        return countor;
    }
    
    public void testCountGenes() {
        int countor = countGenes("ATGTAAGATGCCCTAGT");
        if(countor == 2) {
            System.out.println("success");
        }
    }
}
