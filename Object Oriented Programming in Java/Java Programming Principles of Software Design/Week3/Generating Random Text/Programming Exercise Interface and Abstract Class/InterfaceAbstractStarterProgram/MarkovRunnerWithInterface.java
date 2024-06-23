
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 10);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 11);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 12);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, 13);

    }
    
    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int seed = 615;
        int size = 1000;
        
        EfficientMarkovModel emFive = new EfficientMarkovModel(5);
        runModel(emFive, st, size, seed);
        
        // EfficientMarkovModel em2 = new EfficientMarkovModel(2);
        // runModel(em2, "yes-this-is-a-thin-pretty-pink-thistle", 50, 42);
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int seed = 42;
        int size = 1000;
        
        long startTime1 = System.nanoTime();
        MarkovModel mTwo = new MarkovModel(2);
        runModel(mTwo, st, size, seed);
        long endTime1 = System.nanoTime();
        System.out.println("Elapsed time: " + (endTime1 - startTime1));
        
        long startTime2 = System.nanoTime();
        EfficientMarkovModel emTwo = new EfficientMarkovModel(2);
        runModel(emTwo, st, size, seed);
        long endTime2 = System.nanoTime();
        System.out.println("Elapsed time: " + (endTime2 - startTime2));
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
