package program;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {
    private String dictName;
    private Scanner inputScanner = new Scanner(System.in);
    private HashMap<String, String> a2b;
    private FileManager a2bManager, textManager;

    public Dictionary(String dictName, String filePath) {
        this.dictName = dictName;
        a2b = new HashMap<>();
        try {
            a2bManager = new FileManager(filePath, "dict");
            textManager = new FileManager("text");
        } catch(Exception e) {
            System.out.println("Error building filemanager");
        }


        // v TEST v //
        // a2b.put("Test.txt", "Test.txt Result");
        // ^ TEST ^//

        // check if dict/file combo already exists
        // if exists, add data from file to HashMap
        if(a2bManager.getFile().exists()) {
            a2bManager.readFile();
        } else {
            a2bManager.writeFile();
        }
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
        a2bManager.writeFile();
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

    //======================================================
    // FileManager
    //======================================================

    private class FileManager {
        protected File myFile;
        protected String type; // OPTIONS: dict,text
        protected Scanner fileScanner;
        // determines which read/write methods are used

        public FileManager(String myFilePath, String type) { // fileName requires entire relative path
            this.myFile = new File(myFilePath);
            this.type = type;
            try {
                fileScanner = new Scanner(myFile);
            } catch(Exception e) {
                System.out.println("Error building file scanner");
            }
        }
        public FileManager(String type) {
            this.type = type;
        }

        public String readFile() {
            String result = "ERROR";
            if(type.equalsIgnoreCase("dict")) {
                readDictFile();
            } else if(type.equalsIgnoreCase("text")) {
                result = readTextFile();
            }
            return result;
        }
        private void readDictFile() {
            String key, value;
            fileScanner.useDelimiter(",|\r\n");

            while(fileScanner.hasNext()) {
                key = fileScanner.next();
                value = fileScanner.next();
                System.out.println(key + " : " + value);
                a2b.put(key, value);
            }
        }
        private String readTextFile() {
            String result = "";

            return result;
        }

        public void writeFile() {
            if (type.equalsIgnoreCase("dict")) {
                writeDictFile(myFile);
            } else if (type.equalsIgnoreCase("text")) {
                writeTextFile(myFile, "");
            }
        }
        public void writeFile(String text) {

        }
        private void writeDictFile(File myFile) {
            try {
                FileWriter writer = new FileWriter(myFile, false);
                for(String key : a2b.keySet()) {
                    writer.write(key + "," + a2b.get(key) + "\n");
                }
                writer.close();
            } catch(Exception e) {
                System.out.println("ERROR writing dict file");
            }
        }
        private void writeTextFile(File myFile, String text) {
            try {
                FileWriter writer = new FileWriter(myFile, true);
                writer.write(text);
                writer.close();
            } catch(Exception e) {
                System.out.println("ERROR writing text file");
            }
        }

        public File getFile() {
            return myFile;
        }
    }
}
