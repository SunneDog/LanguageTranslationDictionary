package program;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
	// write your code here
    Scanner inputScanner = new Scanner(System.in);

    Dictionary testDict = new Dictionary("Test", ".");
    testDict.trainDict();
    System.out.print(testDict);
    System.out.println(testDict.readDict());
    inputScanner.close();
    }
}
