package program;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
    Scanner inputScanner = new Scanner(System.in);

    Dictionary testDict = new Dictionary("Test", "./dictionaries/Test.txt");
    testDict.trainDict();
    System.out.print(testDict);
    System.out.println(testDict.readDict());
    inputScanner.close();
    }
}
