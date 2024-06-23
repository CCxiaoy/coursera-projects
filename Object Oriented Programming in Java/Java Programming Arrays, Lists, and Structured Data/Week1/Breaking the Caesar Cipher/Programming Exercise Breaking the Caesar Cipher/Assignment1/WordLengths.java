import edu.duke.*;
/**
 * 在这里给出对类 WordLengths 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class WordLengths {  
    public int indexOfMax(int [] values) {
        int largestIndex = 0;
        for(int i = 0; i < values.length; i++) {
            if(values[i] > values[largestIndex]) {
                largestIndex = i;
            }
        }
        return largestIndex;
    }
    
    public void countWordLengths(FileResource resource, int [] counts) {
        for(String word: resource.words()) {
            boolean [] isAlphat = {false, false};
            if(!Character.isLetter(word.charAt(0))) {
                isAlphat[0] = true;
            }
            if(!Character.isLetter(word.charAt(word.length()-1))) {
                isAlphat[1] = true;
            }
            int wordLength = word.length();
            for(int i = 0; i < isAlphat.length; i++) {
                if(isAlphat[i] && wordLength > 0) {
                    wordLength--;
                }
            }
            counts[wordLength] += 1;
        }
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int [] counts = new int [31];
        countWordLengths(fr, counts);
        int largestIndex = indexOfMax(counts);
        System.out.println("largestIndex in counts array is: \t" + largestIndex);
    }
}
