import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Elior on 2016-08-07.
 */
public @Data class DoubleAlgorithmEncryptionTest {
    @Test
    public void DoubleAlgorithmEncryption_caesar_XOR_Test1(){
        DoubleAlgorithmEncryption DoubleAlgorithmEncryption = new DoubleAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = DoubleAlgorithmEncryption.DoubleAlgorithm(key, byteBuffer, 1, 2);
        assertNotNull(ans);
        byte[] wantedResult = {13,8,10,20};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void DoubleAlgorithmEncryption_XOR_Multiplication_Test2(){
        DoubleAlgorithmEncryption DoubleAlgorithmEncryption = new DoubleAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = DoubleAlgorithmEncryption.DoubleAlgorithm(key, byteBuffer, 2, 3);
        assertNotNull(ans);
        byte[] wantedResult = {30,65,75,45};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void DoubleAlgorithmEncryption_Multiplication__caesar_Test3(){
        DoubleAlgorithmEncryption DoubleAlgorithmEncryption = new DoubleAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = DoubleAlgorithmEncryption.DoubleAlgorithm(key, byteBuffer, 3, 1);
        assertNotNull(ans);
        byte[] wantedResult = {20,45,55,65};
        assertArrayEquals(wantedResult, ans);
    }


    @Test
    public void DoubleAlgorithmEncryption_runAlgorithm_Encryption_caesar_Test3(){
        DoubleAlgorithmEncryption DoubleAlgorithmEncryption = new DoubleAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = DoubleAlgorithmEncryption.runAlgorithm(1, key, key.firstKey, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {8,13,15,17};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void DoubleAlgorithmEncryption_runAlgorithm_Encryption_XOR_Test4(){
        DoubleAlgorithmEncryption DoubleAlgorithmEncryption = new DoubleAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = DoubleAlgorithmEncryption.runAlgorithm(2, key, key.firstKey, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {6,13,15,9};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void DoubleAlgorithmEncryption_runAlgorithm_Encryption_Multiplication_Test5(){
        DoubleAlgorithmEncryption DoubleAlgorithmEncryption = new DoubleAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = DoubleAlgorithmEncryption.runAlgorithm(3, key, key.firstKey, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {15,40,50,60};
        assertArrayEquals(wantedResult, ans);
    }
}