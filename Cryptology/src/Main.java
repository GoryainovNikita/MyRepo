import org.w3c.dom.ls.LSOutput;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Привет! Добро пожаловать в наше приложение \"МегаКриптоанализатор\"!");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            openTheMenu();
            int console = scanner.nextInt();
            if (console == 4) {
                System.out.println("Пока-Пока!");
                break;
            }
            switch (console) {
                case 1: {
                    EnDeCrypt.greeting();
                    break;
                }
                case 2: {
                    System.out.println("Давай попробуем взломать текст очень плохого парня!");
                    BruteForce.decrypt();
                    break;

                }
                case 3: {
                    System.out.println("Давай попытаемся взломать текст!");
                    try {
                        StaticAnalisis.greeting();
                    }
                    catch (FileNotFoundException e){
                        System.out.println("Похоже данного файла не существует( Ты точно прописал .txt ?\n"
                        + "Давай ты все снова проверишь и попробуешь заново)");

                    }
                    catch (IOException e){
                        System.out.println("Что-то пошло не по плану, попробуй перезапустить приложение!");
                    }
                    break;

                }
                default:
                    System.out.println("Похоже, что ты ошибся цифрой, попробуй снова.");
            }
        }
    }

    private static void openTheMenu(){
        System.out.println("Выбери какое действие ты хочешь совершить:\n"
                + "1. Зашифровать/Расшифровать послание\n"
                + "2. Хакнуть зашифрованное послание c помощью Brute Force\n"
                + "3. Хакнуть зашифрованное послание с помощью Статистического Анализа\n"
                + "4. Ничего не хочу, просто выключись!");
    }
}
