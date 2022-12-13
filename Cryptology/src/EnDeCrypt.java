
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class EnDeCrypt {


    public static void greeting() {
        System.out.println("Отлично! Что хочешь выполнить?");

        Scanner scanner = new Scanner(System.in);


        while (true) {
            openTheMenu();
            int console = scanner.nextInt();
            if (console == 3) {
                System.out.println("Ок, как скажешь!");
                break;
            }
            switch (console) {
                case 1: {
                    encrypt();
                    break;
                }
                case 2: {
                    decrypt();
                    break;
                }
                default:
                    System.out.println("Ты видимо опечатался, попробуй снова!");
            }
        }
    }

    private static void openTheMenu(){
        System.out.println("1. Зашифровать послание\n"
                + "2. Расшифровать послание\n"
                + "3. Вернуться к прошлому меню");
    }

    private static void encrypt() {
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


        System.out.println("Укажи файл, который хочешь зашифровать");
        Scanner scanner = new Scanner(System.in);
        String inputPath = scanner.nextLine();

        System.out.println("Укажи файл, куда записать наш бла-бла текст. (Зашифрованный текст)");
        String outputPath = scanner.nextLine();

        System.out.println("Ну и наконец, укажи ключ, которым хочешь зашифровать:\n"
                + "Пссс держи этот ключ в секрете, а то потеряется весь смысл)");
        int key = scanner.nextInt();

        try (var bufferedReader = Files.newBufferedReader(Path.of(inputPath));
             var bufferedWriter = Files.newBufferedWriter(Path.of(outputPath))) {

            char[] chars = new char[65000];
            while (bufferedReader.ready()) {
                int reader = bufferedReader.read(chars);
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] < '\u0410' || chars[i] > '\u044F') {
                        for (int j = 0; j < symbolChar.length; j++) {
                            if (chars[i] == symbolChar[j]) {
                                chars[i] = (char) (chars[i] + key);
                                break;
                            }
                        }
                    } else {
                        chars[i] = addition(chars[i], key);
                    }
                }
                bufferedWriter.write(chars, 0, reader);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Похоже, такого файла не существует, ты точно прописал .txt в названии?");
        } catch (IOException e) {
            System.out.println("Что-то пошло не по плану, попробуй перезапустить приложение!");
        }
        System.out.println("Текст успешно зашифрован!");
    }

    private static void decrypt() {
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
            }

        } catch (FileNotFoundException e) {
            System.out.println("Похоже, такого файла не существует, ты точно прописал .txt в названии?");
        } catch (IOException e) {
            System.out.println("Что-то пошло не по плану, попробуй перезапустить приложение!");
        }

        System.out.println("Текст успешно расшифрован!");
    }

    private static char addition(char a, int key) {

        while (key > 64) {
            key = key - 64;
        }
        char result = (char) (a + key);
        if (result > 'я') {
            int tmp = key - ('я' - a);
            result = (char) ('А' + tmp - 1);
        }


        return result;
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
