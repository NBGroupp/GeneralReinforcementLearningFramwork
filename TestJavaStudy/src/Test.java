/**
 * Created by Mao on 25/10/2016.
 */
import java.util.StringTokenizer;
public class Test {
    public static void main(String[] args){
        String test = "x2y:2.7";
        String head = test.substring(0,test.indexOf(":"));
        double tail = Double.parseDouble(  test.substring(test.indexOf(":") +1,test.length())  );
        System.out.println("head#" + head);
        System.out.println("tail#" + tail);
    }
}
