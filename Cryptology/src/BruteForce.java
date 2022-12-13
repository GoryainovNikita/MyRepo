import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class BruteForce extends EnDeCrypt {

    public static void decrypt() {
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

        int key = 1;
        int tmp1 = 0;

        while (tmp1 == 0) {
            try (var bufferedReader = Files.newBufferedReader(Path.of(inputPath));
                 var bufferedWriter = Files.newBufferedWriter(Path.of(outputPath))) {

                char[] chars = new char[65000];
                while (bufferedReader.ready()) {
                    int reader = bufferedReader.read(chars);
                    for (int i = 0; i < chars.length; i++) {
                        if (chars[i] >= '\u0410' && chars[i] <= '\u044F') {
                            chars[i] = subtraction(chars[i], key);
                        }
                        else{
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
                    for (int i = 0; i < chars.length; i++) {
                        if(i == chars.length - 1){
                            continue;
                        }
                        else if(chars[i] == '.' || chars[i] == '!' || chars[i] == '?' || chars[i] == ';'){
                            if(i == 0){
                                if(chars[i + 1] == ' ' ){
                                    if(chars[i + 2] >= 'А' && chars [i + 2] <= 'Я'){
                                        tmp1++;
                                    }
                                }
                            }
                            else if(chars[i - 1] >= 'а' && chars[i - 1] <= 'я'){
                                if(chars[i + 1] == ' ' ){
                                    if(chars[i + 2] >= 'А' && chars [i + 2] <= 'Я'){
                                        tmp1++;
                                    }
                                }
                            }
                        }
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("Похоже, такого файла не существует, ты точно прописал .txt в названии?");
            } catch (IOException e) {
                System.out.println("Что-то пошло не по плану, попробуй перезапустить приложение!");
            }

            key = key + 1;
        }

        System.out.println("Текст успешно расшифрован! Ключ шифрования - " + (key - 1));

    }



    private static char subtraction(char a, int key) {
        while (key > 64){
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
