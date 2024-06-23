
/**
 * 在这里给出对类 Tester 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）

*/

import edu.duke.*;
import java.util.ArrayList;

public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println("key: t , ArrayList size: " + follows.size() + follows);
        follows = markov.getFollows("e");
        System.out.println("key: e , ArrayList size: " + follows.size() + follows);
        follows = markov.getFollows("es");
        System.out.println("key: es , ArrayList size: " + follows.size() + follows);
        follows = markov.getFollows(".");
        System.out.println("key: . , ArrayList size: " + follows.size() + follows);
        follows = markov.getFollows("t.");
        System.out.println("key: t. , ArrayList size: " + follows.size() + follows);
    }
    
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("he");
        System.out.println("key: he , ArrayList size: " + follows.size());
    }
}
