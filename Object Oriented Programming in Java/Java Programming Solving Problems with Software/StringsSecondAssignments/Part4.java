import edu.duke.*;
/**
 * 在这里给出对类 Part4 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part4 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        // int currentIndex = dna.indexOf(stopCodon, startIndex+3);
        int currentIndex = dna.toUpperCase().indexOf(stopCodon.toUpperCase(), startIndex+3);
        while(currentIndex < dna.length()) {
            if(currentIndex == -1) {
                return dna.length();
            }
            if((currentIndex - startIndex) % 3 == 0) {
                return currentIndex;
            }
            // currentIndex = dna.indexOf(stopCodon, currentIndex+1);
            currentIndex = dna.toUpperCase().indexOf(stopCodon.toUpperCase(), currentIndex+1);
        }
        return dna.length();
    }
    
    public String findGene(String dna) {
        int startIndex = dna.toUpperCase().indexOf("ATG");
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
    
    public StorageResource getAllGenes(String dna) {
        StorageResource genesList = new StorageResource();
        int startPoint = 0;
        while(true) {
            String gene = findGene(dna.substring(startPoint, dna.length()));
            if(gene.isEmpty()) {
                break;
            }
            genesList.add(gene);
            startPoint = dna.indexOf(gene, startPoint) + gene.length();
        }
        return genesList;
    }  
    
    public void testGetAllGenes() {
        StorageResource genesList = getAllGenes("ATGTGACCGATGCCGTGAATG");
        for(String gene : genesList.data()) {
            System.out.println("gene: " + gene); // ATGTGA ATGCCGTGA
        }
    }
    
    public int searchPiece(String str, String piece) {
        int countor = 0;
        int startIndex = 0;
        while(true) {
            int currentIndex = str.toUpperCase().indexOf(piece,startIndex);
            if(currentIndex == -1) {
                break;
            }
            countor++;
            startIndex = currentIndex + piece.length();
        }
        return countor;
    }
 
    public float cgRatio(String dna) {
        float cgRatio = 0;
        int cNum = searchPiece(dna, "C");
        int gNum = searchPiece(dna, "G");
        float cgNum = (float)(cNum + gNum);
        cgRatio = cgNum / dna.length();
        return cgRatio;
    }
    
    public int countCTG(String dna) {
        int ctgNum = searchPiece(dna, "CTG");
        return ctgNum;
    }
    
    public void processGenes(StorageResource sr) {
        int numLonger9 = 0; // the number of Strings in sr that are longer than 9 characters
        int numCGRatio35 = 0; // the number of Strings C-G-ratio is higher than 0.35
        int longestGeneLen = 0; // the longest gene in sr
        for(String str : sr.data()) {
            if(str.length() > 9) {
                System.out.println(">9string: " + str);
                numLonger9++;
            }
            if(cgRatio(str) > 0.35) {
                System.out.println(">0.35CG String: " + str);
                numCGRatio35++;
            }
            if(str.length() > longestGeneLen) {
                longestGeneLen = str.length();
            }
        }
        System.out.println("Num of strs > 9: " + numLonger9);
        System.out.println("Num of strs CG Ratio > 0.35: " + numCGRatio35);
        System.out.println("Longest gene len: " + longestGeneLen);
    }
    
    public void testProcessGenes() {
        StorageResource genesList = new StorageResource();
        genesList.add("ATGATGATGATGTGA"); // >9
        genesList.add("ATGTGA"); // < 9
        genesList.add("ATGCCGCCGTGA"); // >0.35
        genesList.add("ATGATTAAAATTTGA"); // <0.35
        genesList.add("AAAAAAAAAAAAAAAAAAAA"); // longest 20
        processGenes(genesList);
        processGenes(genesList);
        processGenes(genesList);
        processGenes(genesList);
        processGenes(genesList);
    }
    
    public void processGenes1(StorageResource sr) {
        int numLonger60 = 0; // the number of Strings in sr that are longer than 60 characters
        int numCGRatio35 = 0; // the number of Strings C-G-ratio is higher than 0.35
        int longestGeneLen = 0; // the longest gene in sr
        for(String str : sr.data()) {
            if(str.length() > 60) {
                System.out.println(">60string: " + str);
                numLonger60++;
            }
            if(cgRatio(str) > 0.35) {
                System.out.println(">0.35CG String: " + str);
                numCGRatio35++;
            }
            if(str.length() > longestGeneLen) {
                longestGeneLen = str.length();
            }
        }
        System.out.println("Num of strs > 60: " + numLonger60);
        System.out.println("Num of strs CG Ratio > 0.35: " + numCGRatio35);
        System.out.println("Longest gene len: " + longestGeneLen);
    }
    
    public void testProcessGenes1() {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        StorageResource genesList = getAllGenes(dna);
        for(String str : genesList.data()) {
            System.out.println(": " + str);
        }
        processGenes1(genesList);
    }
    
    public void testProcessGenes2() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource genesList = getAllGenes(dna);
        System.out.println("Size: " + genesList.size());
        System.out.println("CTG times: " + countCTG(dna));
        processGenes1(genesList);
    }
}
