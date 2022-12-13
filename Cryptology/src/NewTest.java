public class NewTest {

    public static void main(String[] args) {
        char a = '\u044F';
        char b = '\u0450';

        System.out.println((char) (a + 1));
        System.out.println(b);

        char c = 'А';
        int key = 100;

        while (key > 64) {
            key = key - 64;
        }
        char result = (char) (c + key);
        if (result > 'я') {
            int tmp = key - ('я' - c);
            result = (char) ('А' + tmp - 1);
        }

        System.out.println(result);
    }
}
