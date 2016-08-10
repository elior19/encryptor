import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Elior on 2016-08-07.
 */
public @Data class DoubleAlgorithmDecryptionTest {
    @Test
    public void DoubleAlgorithmDecryption_caesar_XOR_Test1(){
        DoubleAlgorithmDecryption DoubleAlgorithmDecryption = new DoubleAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = DoubleAlgorithmDecryption.DoubleAlgorithm(key, byteBuffer, 1, 2);
        assertNotNull(ans);
        byte[] wantedResult = {1,8,10,4};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void DoubleAlgorithmDecryption_XOR_Multiplication_Test2(){
        DoubleAlgorithmDecryption DoubleAlgorithmDecryption = new DoubleAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = DoubleAlgorithmDecryption.DoubleAlgorithm(key, byteBuffer, 2, 3);
        assertNotNull(ans);
        byte[] wantedResult = {98,109,7,-103};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void DoubleAlgorithmDecryption_Multiplication__caesar_Test3(){
        DoubleAlgorithmDecryption DoubleAlgorithmDecryption = new DoubleAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = DoubleAlgorithmDecryption.DoubleAlgorithm(key, byteBuffer, 3, 1);
        assertNotNull(ans);
        byte[] wantedResult = {102,103,1,-101};
        assertArrayEquals(wantedResult, ans);
    }


    @Test
    public void DoubleAlgorithmDecryption_runAlgorithm_Decryption_caesar_Test3(){
        DoubleAlgorithmDecryption DoubleAlgorithmDecryption = new DoubleAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = DoubleAlgorithmDecryption.runAlgorithm(1, key, key.firstKey, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {-2,3,5,7};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void DoubleAlgorithmDecryption_runAlgorithm_Decryption_XOR_Test4(){
        DoubleAlgorithmDecryption DoubleAlgorithmDecryption = new DoubleAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = DoubleAlgorithmDecryption.runAlgorithm(2, key, key.firstKey, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {6,13,15,9};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void DoubleAlgorithmDecryption_runAlgorithm_Decryption_Multiplication_Test5(){
        DoubleAlgorithmDecryption DoubleAlgorithmDecryption = new DoubleAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = DoubleAlgorithmDecryption.runAlgorithm(3, key, key.firstKey, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {103,104,2,-100};
        assertArrayEquals(wantedResult, ans);
    }
}