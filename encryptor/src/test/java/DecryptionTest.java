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
public @Data class DecryptionTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void readFileContent_DecryptionTest2() throws IOException {
        File file = temporaryFolder.newFile("fileTest.txt");
        byte[] wantedResult = null;
        Decryption Decryption = new Decryption();
        byte[] ans = Decryption.ReadFileContent("fileTest.txt");
        assertArrayEquals(wantedResult, ans);
        temporaryFolder.delete();

    }

    @Test
    public void runTheAlgorithm_Decryption_caesarAlgorithmDecryption_Test3(){
        caesarAlgorithmDecryptionTest caesarAlgorithmDecryptionTest = new caesarAlgorithmDecryptionTest();
        caesarAlgorithmDecryptionTest.caesarAlgorithmDecryptionTest1();
    }

    @Test
    public void runTheAlgorithm_Decryption_XORAlgorithmDecryptionTest_Test4(){
        XORAlgorithmDecryptionTest XORAlgorithmDecryptionTest = new XORAlgorithmDecryptionTest();
        XORAlgorithmDecryptionTest.XORAlgorithmDecryptionTest1();
    }


    @Test
    public void runTheAlgorithm_Decryption_MultiplicationAlgorithmDecryptionTest_Test5(){
        MultiplicationAlgorithmDecryptionTest MultiplicationAlgorithmDecryptionTest = new MultiplicationAlgorithmDecryptionTest();
        MultiplicationAlgorithmDecryptionTest.MultiplicationAlgorithmDecryptionTest1();
    }


    @Test
    public void runTheAlgorithm_Decryption_DoubleAlgorithmDecryptionTest_Test6(){
        DoubleAlgorithmDecryptionTest DoubleAlgorithmDecryptionTest = new DoubleAlgorithmDecryptionTest();
        DoubleAlgorithmDecryptionTest.DoubleAlgorithmDecryption_caesar_XOR_Test1();
    }


    @Test
    public void runTheAlgorithm_Decryption_ReverseAlgorithmDecryptionTest_Test7(){
        ReverseAlgorithmDecryptionTest ReverseAlgorithmDecryptionTest = new ReverseAlgorithmDecryptionTest();
        ReverseAlgorithmDecryptionTest.ReverseAlgorithmDecryption_caesar_Test1();
    }

    @Test
    public void runTheAlgorithm_Decryption_SplitAlgorithmDecryptionTest_Test8(){
        SplitAlgorithmDecryptionTest SplitAlgorithmDecryptionTest = new SplitAlgorithmDecryptionTest();
        SplitAlgorithmDecryptionTest.SplitAlgorithmDecryption_caesar_Test1();
    }
}