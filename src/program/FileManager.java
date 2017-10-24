package program;

import java.io.File;

// manages files for Main and Dictionary
public class FileManager {
    File myFile;
    String type; // OPTIONS: dict,text
    // determines which read/write methods are used

    public FileManager(String fileName, String type) { // fileName requires entire relative path
        myFile = new File(fileName);
        this.type = type;
    }
}
