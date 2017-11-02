package program;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
	// write your code here
        Scanner inputScanner = new Scanner(System.in);
        int userInput = 0;
        Dictionary testDict = new Dictionary("Test.txt",
                "C:\\Users\\crazs\\Desktop\\FLASH DRIVE\\Language Translation Dictionary\\src\\program\\dictionaries\\Test.txt");

        while(userInput != 4) {
            System.out.println("1. Train Dictionary\n" + "2. Translate word/sentence\n"
                    + "3. Translate File\n" + "4. Quit");
            userInput = inputScanner.nextInt();

            if(userInput == 1) {
               testDict.trainDict();
            } else if(userInput == 2) {
                System.out.println(testDict.readDict());
            } else if(userInput == 3) {
                testDict.translateFile("C:\\Users\\crazs\\Desktop\\FLASH DRIVE\\Language Translation Dictionary\\src\\program\\translations\\TranslatableText.txt",
                    "C:\\Users\\crazs\\Desktop\\FLASH DRIVE\\Language Translation Dictionary\\src\\program\\translations\\TranslatedTranslatableText.txt");
            } else if(userInput == 4) {
                System.out.println("Goodbye");
            }
        }
    inputScanner.close();
    }
}
