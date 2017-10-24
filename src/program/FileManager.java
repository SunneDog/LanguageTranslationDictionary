package program;

import java.io.File;

// manages files for Main and Dictionary
public class FileManager {
    File myFile;

    public FileManager(String fileName) { // fileName requires entire relative path
        myFile = new File(fileName);
    }
}
