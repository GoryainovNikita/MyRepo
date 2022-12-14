import java.util.Arrays;

public class NewTest {

    public static void main(String[] args) {
        String sentence = "Привет! меня зовут Павлик!";


        String[] rawWords = sentence.split("(?<=[\\s|(','&&'\\s')])");




        for (int i = 0; i < rawWords.length; i++) {
            System.out.println(rawWords[i].length());
        }



        System.out.println(Arrays.deepToString(rawWords));
    }

}
