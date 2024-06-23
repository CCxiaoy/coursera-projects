import edu.duke.*;
import java.util.*;

/**
 * 在这里给出对类 GladLibMap 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private String[] wordCategories;
    
    private ArrayList<String> wordsUsedList;
    private ArrayList<String> categoryUsedList;
    private int totalReplace;
        
    private Random myRandom;
        
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
        
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
        
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
        
    private void initializeFromSource(String source) {
        categoryUsedList = new ArrayList<String>();
        myMap = new HashMap<String, ArrayList<String>>();
        wordCategories = new String[] {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        
        for(int i = 0; i < wordCategories.length; i++) {
            String wordCategory = wordCategories[i];
            myMap.put((wordCategory + "List"), readIt(source+"/" + wordCategory + ".txt"));
        }
        
        wordsUsedList = new ArrayList<String>();
        
        totalReplace = 0;
    }
        
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        for(int i = 0; i < wordCategories.length; i++) {
            String wordCategory = wordCategories[i];
            if(label.equals(wordCategory)) {
                if(!categoryUsedList.contains(wordCategory + "List")) {
                    categoryUsedList.add(wordCategory + "List");
                }
                return randomFrom(myMap.get((wordCategory + "List")));
            }
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub;
        do {
            sub = getSubstitute(w.substring(first+1,last));
        } 
        while (wordsUsedList.contains(sub));
        totalReplace++;
        wordsUsedList.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap() {
        int totalWords = 0;
        for(String key : myMap.keySet()) {
            totalWords += (myMap.get(key).size());
        }
        return totalWords;
    }
    
    public int totalWordsConsidered() {
        int totalWords = 0;
        for(int i = 0; i < categoryUsedList.size(); i++) {
            System.out.println(categoryUsedList.get(i));
            totalWords += myMap.get(categoryUsedList.get(i)).size();
        }
        return totalWords;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nTotal Number of words were replaced: " + totalReplace);
        
        int totalWords = totalWordsInMap();
        System.out.println("Ttotal number of words that were possible to pick from: " + totalWords);
        int wordsSelectedCategories = totalWordsConsidered();
        System.out.println("Ttotal number of words in the ArrayLists of the categories that were used: " + wordsSelectedCategories);
        
        wordsUsedList.clear();
        categoryUsedList.clear();
        totalReplace = 0;
    }
}
