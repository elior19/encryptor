import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Elior on 2016-08-06.
 */
public @Data class caesarAlgorithmEncryptionTest {
    @Test
    public void caesarAlgorithmEncryptionTest1(){
        caesarAlgorithmEncryption caesarAlgorithmEncryption = new caesarAlgorithmEncryption();
        byte key = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = caesarAlgorithmEncryption.CaesarAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {8,13,15,17};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void caesarAlgorithmEncryptionTest2(){
        caesarAlgorithmEncryption caesarAlgorithmEncryption = new caesarAlgorithmEncryption();
        byte key = 0;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = caesarAlgorithmEncryption.CaesarAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {3,8,10,12};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void caesarAlgorithmEncryptionTest3(){
        caesarAlgorithmEncryption caesarAlgorithmEncryption = new caesarAlgorithmEncryption();
        byte key = 50;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = caesarAlgorithmEncryption.CaesarAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {53,58,60,62};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void caesarAlgorithmEncryptionTest4(){
        caesarAlgorithmEncryption caesarAlgorithmEncryption = new caesarAlgorithmEncryption();
        byte key = 50;
        byte[] byteBuffer = {3,8,10,127};
        byte[] ans = caesarAlgorithmEncryption.CaesarAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {53,58,60,-79};
        assertArrayEquals(wantedResult, ans);
    }
}