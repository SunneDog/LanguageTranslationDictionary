package program;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {
    private String dictName;
    private Scanner inputScanner = new Scanner(System.in);
    private HashMap<String, String> a2b;
    private String filePath; // where the dictionary is stored + loaded from
    //private FileManager a2bManager;

    public Dictionary(String dictName, String filePath) throws FileNotFoundException {
        this.dictName = dictName;
        this.filePath = filePath;
        a2b = new HashMap<String, String>();
        //a2bManager = new FileManager(filePath, "dict");

        // v TEST v //
        // a2b.put("Test", "Test Result");
        // ^ TEST ^//

        // check if dict/file combo already exists

        // if exists, add data from file to HashMap

        // else create new file for new dict
    }

    public void trainDict() {
        String key;
        String value;

        System.out.println("Enter nothing to quit");
        while (true) {
            System.out.println("What word are you translating FROM?");
            key = inputScanner.nextLine();
            if(key.equalsIgnoreCase("")) {
                break;
            }
            System.out.println("What word are you translating TO?");
            value = inputScanner.nextLine();

            System.out.println(key + " : " + value);
            a2b.put(key, value);
        }
    }

    public String readDict() {
        String userKey, result;

        System.out.println("Enter nothing to quit");
        System.out.println("Word or sentence to translate: ");
        userKey = inputScanner.nextLine();
        result = translateInput(userKey);
        return result;
    }
    private String translateInput(String userKey) {
        String result = "";
        String[] results = userKey.split(" ");
        if (userKey.equalsIgnoreCase("")) {
            return "";
        } else {
            for(String word : results) {
                result += getValueFromKey(word) + " ";
            }
        }
        return result;
    }
    private String getValueFromKey(String key) {
        if(a2b.containsKey(key)) {
            return a2b.get(key);
        } else {
            return key;
        }
    }

    @Override
    // stolen from sfarzo
    public String toString() {
        String s = new String(this.dictName);
        s = s.concat(" dictionary:\n");

        for(String key : a2b.keySet()){
            s = s.concat(key + " : " + a2b.get(key) + "\n");
        }

        return s;
    }
/*
    private class FileManager {
        File myFile;
        String type; // OPTIONS: dict,text
        Scanner fileScanner;
        // determines which read/write methods are used

        public FileManager(String fileName, String type) throws FileNotFoundException { // fileName requires entire relative path
            myFile = new File(fileName);
            this.type = type;
                fileScanner = new Scanner(myFile);
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
    }*/
}
