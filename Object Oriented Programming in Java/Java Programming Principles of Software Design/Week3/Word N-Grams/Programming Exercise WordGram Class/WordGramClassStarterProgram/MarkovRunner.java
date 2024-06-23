
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(3);
        runModel(markovWord, st, 200, 643); 
    } 
    
    public void testHashMap() {
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        // MarkovWord markovWord = new MarkovWord(5);
        // runModel(markovWord, st, 100, 844);
        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(5);
        runModel(efficientMarkovWord, st, 100, 531);
        
        // EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);
        // runModel(efficientMarkovWord, "this is a test yes this is really a test", 50, 42);
        // runModel(efficientMarkovWord, "this is a test yes this is really a test yes a test this is wow", 50, 42);
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
                
        MarkovWord markovWord = new MarkovWord(2);
        long startTime1 = System.nanoTime();
        runModel(markovWord, st, 100, 42);
        long endTime1 = System.nanoTime();
        System.out.println("MarkovWord time consumes" + (endTime1-startTime1));
        
        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(2);
        long startTime2 = System.nanoTime();
        runModel(efficientMarkovWord, st, 100, 42);
        long endTime2 = System.nanoTime();
        System.out.println("EfficientMarkovWord time consumes" + (endTime2-startTime2));
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
