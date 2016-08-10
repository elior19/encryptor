import lombok.Data;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Elior on 2016-08-07.
 */
public @Data class EncryptionTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void handelKey_EncryptionTest1() throws IOException {
        Encryption Encryption = new Encryption();
        Encryption.path = "";
        Encryption.fileOrDir = 1;
        Encryption.HandelKey();
        Key ans = Encryption.key;
        assertNotNull(ans);
        File file = new File("key.bin");
        assertEquals(file.exists(),true);
    }

    @Test
    public void readFileContent_EncryptionTest2() throws IOException {
        File file = temporaryFolder.newFile("fileTest.txt");
        byte[] wantedResult = null;
        Encryption Encryption = new Encryption();
        byte[] ans = Encryption.ReadFileContent("fileTest.txt");
        assertArrayEquals(wantedResult, ans);
        temporaryFolder.delete();

    }

    @Test
    public void runTheAlgorithm_Encryption_caesarAlgorithmEncryption_Test3(){
        caesarAlgorithmEncryptionTest caesarAlgorithmEncryptionTest = new caesarAlgorithmEncryptionTest();
        caesarAlgorithmEncryptionTest.caesarAlgorithmEncryptionTest1();
    }

    @Test
    public void runTheAlgorithm_Encryption_XORAlgorithmEncryptionTest_Test4(){
        XORAlgorithmEncryptionTest XORAlgorithmEncryptionTest = new XORAlgorithmEncryptionTest();
        XORAlgorithmEncryptionTest.XORAlgorithmEncryptionTest1();
    }


    @Test
    public void runTheAlgorithm_Encryption_MultiplicationAlgorithmEncryptionTest_Test5(){
        MultiplicationAlgorithmEncryptionTest MultiplicationAlgorithmEncryptionTest = new MultiplicationAlgorithmEncryptionTest();
        MultiplicationAlgorithmEncryptionTest.MultiplicationAlgorithmEncryptionTest1();
    }


    @Test
    public void runTheAlgorithm_Encryption_DoubleAlgorithmEncryptionTest_Test6(){
        DoubleAlgorithmEncryptionTest DoubleAlgorithmEncryptionTest = new DoubleAlgorithmEncryptionTest();
        DoubleAlgorithmEncryptionTest.DoubleAlgorithmEncryption_caesar_XOR_Test1();
    }


    @Test
    public void runTheAlgorithm_Encryption_ReverseAlgorithmEncryptionTest_Test7(){
        ReverseAlgorithmEncryptionTest ReverseAlgorithmEncryptionTest = new ReverseAlgorithmEncryptionTest();
        ReverseAlgorithmEncryptionTest.ReverseAlgorithmEncryption_caesar_Test1();
    }

    @Test
    public void runTheAlgorithm_Encryption_SplitAlgorithmEncryptionTest_Test8(){
        SplitAlgorithmEncryptionTest SplitAlgorithmEncryptionTest = new SplitAlgorithmEncryptionTest();
        SplitAlgorithmEncryptionTest.SplitAlgorithmEncryption_caesar_Test1();
    }

}