package program;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        } catch(Exception e) {
            System.out.println("Error building filemanager; resorting to default");
            a2bManager = new FileManager("C:\\Users\\crazs\\Desktop\\FLASH DRIVE\\Language Translation Dictionary\\src\\program\\dictionaries\\Test.txt","dict");
        }

        // check if dict/file combo already exists
        // if exists, add data from file to HashMap
        if(a2bManager.getFile().exists()) {
            a2bManager.readFile();
        } else {
            a2bManager.writeFile();
        }
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

    public void translateFile(String filePath, String newFilePath) {
        String result;
        textManager = new FileManager(filePath, "text");

        result = textManager.textContent;
        System.out.println("Translating: " + result);
        result = translateInput(result);
        System.out.println("Translation: " + result);
        System.out.println("Saving to: " + newFilePath);
        textManager.writeTextFile(new File(newFilePath), result);
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
        protected String newFilePath; // for translated text files
        protected String type; // OPTIONS: dict,text || determins which read/writes to use
        protected String textContent; // if FileManager is for a text file, this is where the text goes
        protected Scanner fileScanner;


        public FileManager(String myFilePath, String type) { // fileName requires entire relative pa
            this.myFile = new File(myFilePath);
            this.type = type;
            try {
                fileScanner = new Scanner(myFile);
            } catch (FileNotFoundException e) {
                System.out.println("Error building file scanner");
            }
            this.newFilePath = myFilePath + "Translated.txt";
            readFile();
        }

        public String readFile() {
            String result = null;
            if(type.equalsIgnoreCase("dict")) {
                readDictFile();
            } else if(type.equalsIgnoreCase("text")) {
                result = readTextFile();
            }
            return result;
        }
        private void readDictFile() {
            String key, value;
            fileScanner.useDelimiter(",|\n");

            while(fileScanner.hasNext()) {
                key = fileScanner.next();
                value = fileScanner.next();
                System.out.println(key + " : " + value);
                a2b.put(key, value);
            }
        }
        private String readTextFile() {
            String result = "";
            fileScanner.useDelimiter(" ");
            while(fileScanner.hasNext()) {
                result += fileScanner.next() + " ";
            }
            System.out.println(result);
            this.textContent = result;
            return result;
        }

        public void writeFile() {
            if (type.equalsIgnoreCase("dict")) {
                writeDictFile(myFile);
            } else if (type.equalsIgnoreCase("text")) {
                File newTextFile = new File(newFilePath);
                writeTextFile(newTextFile, textContent);
            }
        }
        private void writeDictFile(File myFile) {
            try {
                FileWriter writer = new FileWriter(myFile, false);
                for(String key : a2b.keySet()) {
                    writer.write(key + "," + a2b.get(key) + "\n");
                }
                writer.close();
            } catch(IOException e) {
                System.out.println("ERROR writing dict file");
            }
        }
        private void writeTextFile(File myFile, String text) {
            try {
                FileWriter writer = new FileWriter(myFile);
                writer.write(text);
                writer.close();
            } catch(IOException e) {
                e.printStackTrace();
                System.out.println("ERROR writing text file");
            }
        }

        public File getFile() {
            return myFile;
        }
    }
}
