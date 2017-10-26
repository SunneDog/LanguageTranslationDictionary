package program;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
	// write your code here
    Scanner inputScanner = new Scanner(System.in);

    Dictionary testDict = new Dictionary("Test.txt",
            ".\\src\\program\\dictionaries\\Test.txt");
    testDict.trainDict();
    System.out.println(testDict.readDict());
    inputScanner.close();
    }
}
