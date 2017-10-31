package program;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
	// write your code here
    Scanner inputScanner = new Scanner(System.in);

    Dictionary testDict = new Dictionary("Test.txt",
            "C:\\Users\\crazs\\Desktop\\FLASH DRIVE\\Language Translation Dictionary\\src\\program\\dictionaries\\Test.txt");
    testDict.trainDict();
    System.out.println(testDict.readDict());
    testDict.translateFile("C:\\Users\\crazs\\Desktop\\FLASH DRIVE\\Language Translation Dictionary\\src\\program\\translations\\TranslatableText.txt",
            "C:\\Users\\crazs\\Desktop\\FLASH DRIVE\\Language Translation Dictionary\\src\\translations\\" + "Translated" + "TranslatableText.txt");
    inputScanner.close();
    }
}
