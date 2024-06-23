import java.util.*;
import java.io.File;
import edu.duke.*;

/**
 * 在这里给出对类 WordsInFiles 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordMap;
    
    WordsInFiles() {
        wordMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        for(String word : fr.words()) {
            if(!wordMap.containsKey(word)) {
                ArrayList<String> fileNameList = new ArrayList<String>();
                fileNameList.add(fileName);
                wordMap.put(word, fileNameList);
            } else {
                if(!(wordMap.get(word).contains(fileName))) {
                    wordMap.get(word).add(fileName);
                }
            }
        }
    }
    
    public void buildWordFileMap() {
        wordMap.clear(); // Ensure map empty before any operations
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    // all the three methods below 
    public int maxNumber() {
        int maxNum = 0;
        for(String word : wordMap.keySet()) {
            int currentSize = wordMap.get(word).size();
            if(currentSize > maxNum) {
                maxNum = currentSize;
            }
        }
        return maxNum;
    }
    
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordsList = new ArrayList<String>();
        for(String word : wordMap.keySet()) {
            if((wordMap.get(word).size()) == number) {
                wordsList.add(word);
            }
        }
        return wordsList;
    }
    
    public void printFilesIn(String word) {
        ArrayList<String> fileNameList = wordMap.get(word);
        for(int i = 0; i < fileNameList.size(); i++) {
            System.out.println(fileNameList.get(i));
        }
    }
    
    public void printAllDetails() {
        for(String word : wordMap.keySet()) {
            System.out.println(word + " apears in ");
            for(int i = 0; i < wordMap.get(word).size(); i++) {
                System.out.print(wordMap.get(word).get(i));
            }
        }
    }
    
    public void wordsInFilesTester() {
        buildWordFileMap();
        // int maxNum = maxNumber();
        // ArrayList<String> wordsList = wordsInNumFiles(maxNum);
        printFilesIn("tree");
        // for(int i = 0; i < wordsList.size(); i++) {
        //    System.out.println(wordsList.get(i) + " : ");
        //    printFilesIn(wordsList.get(i));
        // }
    }
}
