import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Elior on 2016-08-06.
 */
public @Data class caesarAlgorithmDecryptionTest {
    @Test
    public void caesarAlgorithmDecryptionTest1(){
        caesarAlgorithmDecryption caesarAlgorithmDecryption = new caesarAlgorithmDecryption();
        byte key = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = caesarAlgorithmDecryption.CaesarAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {-2,3,5,7};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void caesarAlgorithmDecryptionTest2(){
        caesarAlgorithmDecryption caesarAlgorithmDecryption = new caesarAlgorithmDecryption();
        byte key = 0;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = caesarAlgorithmDecryption.CaesarAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {3,8,10,12};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void caesarAlgorithmDecryptionTest3(){
        caesarAlgorithmDecryption caesarAlgorithmDecryption = new caesarAlgorithmDecryption();
        byte key = 50;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = caesarAlgorithmDecryption.CaesarAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {-47,-42,-40,-38};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void caesarAlgorithmDecryptionTest4(){
        caesarAlgorithmDecryption caesarAlgorithmDecryption = new caesarAlgorithmDecryption();
        byte key = 50;
        byte[] byteBuffer = {3,8,10,127};
        byte[] ans = caesarAlgorithmDecryption.CaesarAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {-47,-42,-40,77};
        assertArrayEquals(wantedResult, ans);
    }
}