package program;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {
    private String dictName;
    private HashMap<String, String> a2b;
    private String filePath; // where the dictionary is stored + loaded from
    private FileManager a2bManager;
    private Scanner trainScanner;
    public Dictionary(String dictName, String filePath) {
        this.dictName = dictName;
        this.filePath = filePath;
        a2b = new HashMap<String, String>();
        a2bManager = new FileManager(filePath, "dict");

        // v TEST v //
        // a2b.put("Test", "Test Result");
        // ^ TEST ^//

        // check if dict/file combo already exists

        // if exists, add data from file to HashMap

        // else create new file for new dict
    }

    public void trainDict() {
        Scanner trainScanner = new Scanner(System.in);
        String key, value, yn;
        boolean check = true;

        while (check == true) {
            System.out.println("What word are you translating FROM?");
            key = trainScanner.nextLine();
            System.out.println("What word are you translating TO?");
            value = trainScanner.nextLine();

            System.out.println(key + " : " + value);
            a2b.put(key, value);

            System.out.println("Do you want to translate another word? (y/n)");
            yn = trainScanner.nextLine();
            if (yn.equalsIgnoreCase("y")) {
                check = true;
            } else if (yn.equalsIgnoreCase("n")) {
                check = false;
            }

        }
        trainScanner.close();
    }


    @Override
    // stolen from sfarzo
    public String toString() {
        String s = new String(this.dictName);
        s = s.concat(" dictionary:\n");

        for(String key : a2b.keySet()){
            s = s.concat(key + " : " + a2b.get(key));
        }

        return s;
    }

    private class FileManager {
        File myFile;
        String type; // OPTIONS: dict,text
        // determines which read/write methods are used

        public FileManager(String fileName, String type) { // fileName requires entire relative path
            myFile = new File(fileName);
            this.type = type;
        }

        public String readFile() {
            String result = "ERROR";
            if(this.type.equalsIgnoreCase("dict")) {
                result = readDict();
            } else if(this.type.equalsIgnoreCase("text")) {
                result = readText();
            }
            return result;
        }
        private String readDict() {
            String result = "";

            return result;
        }
        private String readText() {
            String result = "";

            return result;
        }

        public void writeFile(String fileName) {

        }
    }
}
