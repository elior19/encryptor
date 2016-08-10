
import lombok.Data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Elior on 2016-08-05.
 */
public @Data class generateLargeFile {
    public static void main(String[] args){
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("largeFile.txt", "rw");
            file.setLength(3 * 1024 * 1024); // (~3MB)
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

