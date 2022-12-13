import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        char[] symbolChar = new char[35];
        symbolChar[0] = 'Ё';
        symbolChar[1] = 'ё';
        symbolChar[2] = '.';
        symbolChar[3] = ',';
        symbolChar[4] = '!';
        symbolChar[5] = '?';
        symbolChar[6] = '-';
        symbolChar[7] = ':';
        symbolChar[8] = ';';
        symbolChar[9] = '"';
        symbolChar[10] = '(';
        symbolChar[11] = ')';
        symbolChar[12] = '1';
        symbolChar[13] = '2';
        symbolChar[14] = '3';
        symbolChar[15] = '4';
        symbolChar[16] = '5';
        symbolChar[17] = '6';
        symbolChar[18] = '7';
        symbolChar[19] = '8';
        symbolChar[20] = '9';
        symbolChar[21] = '0';
        symbolChar[22] = ' ';
        symbolChar[23] = '\t';
        symbolChar[24] = '\r';
        symbolChar[25] = '\n';
        symbolChar[26] = '\f';
        symbolChar[27] = '\'';
        symbolChar[28] = '\\';
        symbolChar[29] = '/';
        symbolChar[30] = '«';
        symbolChar[31] = '»';
        symbolChar[32] = '–';
        symbolChar[33] = '…';
        symbolChar[34] = '—';


        System.out.println("Укажи файл, который хочешь расшифровать");
        Scanner scanner = new Scanner(System.in);
        String inputPath = scanner.nextLine();
        System.out.println("Укажи файл, куда записать расшифрованный текст");
        String outputPath = scanner.nextLine();
        System.out.println("Тебе должны были шепнуть на ушко секретный ключ, которым зашифрован файл.\n"
                + "Расскажи его мне, иначе я не смогу тебе помочь(\n"
                + "P.s Обещаю, я никому не расскажу)");
        int key = scanner.nextInt();

        try (var bufferedReader = Files.newBufferedReader(Path.of(inputPath));
             var bufferedWriter = Files.newBufferedWriter(Path.of(outputPath))) {

            char[] chars = new char[65000];
            while (bufferedReader.ready()) {
                int reader = bufferedReader.read(chars);
                for (int i = 0; i < chars.length; i++) {

                    if (chars[i] >= '\u0410' && chars[i] <= '\u044F') {
                        chars[i] = subtraction(chars[i], key);
                    } else {
                        char tmp = (char) (chars[i] - key);
                        for (int j = 0; j < symbolChar.length; j++) {
                            if (tmp == symbolChar[j]) {
                                chars[i] = tmp;
                                break;
                            }
                        }
                    }


                }
                bufferedWriter.write(chars, 0, reader);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Похоже, такого файла не существует, ты точно прописал .txt в названии?");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Текст успешно расшифрован!");
    }

    private static char subtraction(char a, int key) {
        while (key > 64) {
            key = key - 64;
        }
        char result = (char) (a - key);
        if (result < 'А') {
            int tmp = key - (a - 'А');
            result = (char) ('я' - tmp + 1);
        }
        return result;
    }

}


