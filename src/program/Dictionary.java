package program;

import java.util.HashMap;

public class Dictionary {
    String dictName;
    HashMap<String, String> a2b;
    String filePath; // where the dictionary is stored + loaded from

    public Dictionary(String dictName, String filePath) {
        this.dictName = dictName;
        this.filePath = filePath;
        // check if dict/file combo already exists

        // if exists, add data from file to HashMap

        // else create new file for new dict
    }
}
