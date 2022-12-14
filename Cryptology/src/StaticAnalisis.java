import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class StaticAnalisis {
    public static void greeting() throws IOException {
        System.out.println("Укажи файл, на основе которого провести статистический анализ");
        Scanner scanner = new Scanner(System.in);
        String source = scanner.nextLine();

        HashMap<Character,Integer> hashMapSource = calculatingThePercentage(source);
        System.out.println("Укажи файл, который необходимо расшифровать");
        String decrypted = scanner.nextLine();
        System.out.println("Укажи файл, куда записать расшифровку");
        String result = scanner.nextLine();

        statistic(decrypted, result, hashMapSource);

        System.out.println("Расшифровка записана.");

    }

    public static HashMap<Character, Integer> calculatingThePercentage(String console) throws IOException {
        Path file = Path.of(console);
        String string = Files.readString(file);
        LetterCount letterCount = new LetterCount(string);
        letterCount.counterAllWords();

        HashMap<Character, Integer> hashMapCountryWords = letterCount.counterAllWords();


        int[] x = {1};

        hashMapCountryWords.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(e -> hashMapCountryWords.put(e.getKey(), x[0]++));

        System.out.println(hashMapCountryWords);



        return hashMapCountryWords;
    }

    private static void statistic(String decrypt, String result, HashMap<Character, Integer> source) throws IOException {
        HashMap<Character, Integer> hashMapDecrypted = calculatingThePercentage(decrypt);

        try (var bufferedReader = Files.newBufferedReader(Path.of(decrypt));
             var bufferedWriter = Files.newBufferedWriter(Path.of(result))) {
            char[] chars = new char[65000];
            while (bufferedReader.ready()) {
                int reader = bufferedReader.read(chars);
                for (int i = 0; i < chars.length; i++) {
                        for (var elem : hashMapDecrypted.entrySet()) {
                            if (elem.getKey() == chars[i]) {
                                for (var elem1 : source.entrySet()) {
                                    if(elem.getValue() == elem1.getValue()){
                                        chars[i] = elem1.getKey();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                }
                bufferedWriter.write(chars, 0, reader);
            }
        }
    }
}


