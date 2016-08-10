import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Elior on 2016-08-07.
 */
public @Data class SplitAlgorithmDecryptionTest {
    @Test
    public void SplitAlgorithmDecryption_caesar_Test1(){
        SplitAlgorithmDecryption SplitAlgorithmDecryption = new SplitAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmDecryption.SplitAlgorithm(key, byteBuffer, 1);
        assertNotNull(ans);
        byte[] wantedResult = {-2,3,5,7};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void SplitAlgorithmDecryption_XOR_Test2(){
        SplitAlgorithmDecryption SplitAlgorithmDecryption = new SplitAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmDecryption.SplitAlgorithm(key, byteBuffer, 2);
        assertNotNull(ans);
        byte[] wantedResult = {6,13,15,9};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void SplitAlgorithmDecryption_Multiplication_Test3(){
        SplitAlgorithmDecryption SplitAlgorithmDecryption = new SplitAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmDecryption.SplitAlgorithm(key, byteBuffer, 3);
        assertNotNull(ans);
        byte[] wantedResult = {103,104,2,-100};
        assertArrayEquals(wantedResult, ans);
    }


    @Test
    public void SplitAlgorithmDecryption_runAlgorithm_Decryption_caesar_Test3(){
        SplitAlgorithmDecryption SplitAlgorithmDecryption = new SplitAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmDecryption.runAlgorithm(1, key, key.firstKey, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {-2,3,5,7};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void SplitAlgorithmDecryption_runAlgorithm_Decryption_XOR_Test4(){
        SplitAlgorithmDecryption SplitAlgorithmDecryption = new SplitAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmDecryption.runAlgorithm(2, key, key.firstKey, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {6,13,15,9};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void SplitAlgorithmDecryption_runAlgorithm_Decryption_Multiplication_Test5(){
        SplitAlgorithmDecryption SplitAlgorithmDecryption = new SplitAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmDecryption.runAlgorithm(3, key, key.firstKey, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {103,104,2,-100};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void SplitAlgorithmDecryption_SplitBufferOdd_DecryptionTest6(){
        SplitAlgorithmDecryption SplitAlgorithmDecryption = new SplitAlgorithmDecryption();
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmDecryption.SplitBufferOdd(byteBuffer);
        assertNotNull(ans);
        byte[] expected = {8,12};
        assertArrayEquals(expected, ans);
    }

    @Test
    public void SplitAlgorithmDecryption_SplitBufferEven_DecryptionTest7(){
        SplitAlgorithmDecryption SplitAlgorithmDecryption = new SplitAlgorithmDecryption();
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmDecryption.SplitBufferEven(byteBuffer);
        assertNotNull(ans);
        byte[] expected = {3,10};
        assertArrayEquals(expected, ans);
    }

    @Test
    public void SplitAlgorithmDecryption_SplitBufferEven_DecryptionTest8(){
        SplitAlgorithmDecryption SplitAlgorithmDecryption = new SplitAlgorithmDecryption();
        byte[] byteBuffer = {3,8,10,12,14};
        byte[] ans = SplitAlgorithmDecryption.SplitBufferEven(byteBuffer);
        assertNotNull(ans);
        byte[] expected = {3,10,14};
        assertArrayEquals(expected, ans);
    }
}