
import lombok.Data;

import java.io.File;

/**
 * Created by Elior on 2016-08-05.
 */
public @Data class FileThreadDecryption extends Decryption implements Runnable {

    public Key key = new Key();
    public String path;
    public int algorithmNumber;
    public int firstAlgorithmNumber;
    public int secondAlgorithmNumber;
    public File currFile;
    public boolean choose;

    public FileThreadDecryption(Key keyToSave, String pathToSave, int algorithmNumberToSave, File fileToSave, Boolean chooseToSave, Boolean isWakeupNeededToSave) {
        key = keyToSave;
        path = pathToSave;
        algorithmNumber = algorithmNumberToSave;
        currFile = fileToSave;
        choose = chooseToSave;
        isWakeupNeeded = isWakeupNeededToSave;
    }

    public void run() {

        if(!choose){
            synchronized (lock){
                try {
                    while (!isWakeupNeeded) {
                        lock.wait(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        String fileName = currFile.getName();
        if(!(fileName.equals("key.bin")) && !(fileName.equals("encrypted")) && !(fileName.equals("decrypted")) && !(currFile.isDirectory())) {
            byte[] ans = runTheAlgorithm(ReadFileContent(currFile.getPath()), algorithmNumber, key, choose);
            GenerateNewFileAndPrintToIt(ans, fileName, path, 2);
        }
    }

}

