import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Elior on 2016-08-07.
 */
public @Data class SplitAlgorithmEncryptionTest {
    @Test
    public void SplitAlgorithmEncryption_caesar_Test1(){
        SplitAlgorithmEncryption SplitAlgorithmEncryption = new SplitAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmEncryption.SplitAlgorithm(key, byteBuffer, 1);
        assertNotNull(ans);
        byte[] wantedResult = {8,13,15,17};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void SplitAlgorithmEncryption_XOR_Test2(){
        SplitAlgorithmEncryption SplitAlgorithmEncryption = new SplitAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmEncryption.SplitAlgorithm(key, byteBuffer, 2);
        assertNotNull(ans);
        byte[] wantedResult = {6,13,15,9};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void SplitAlgorithmEncryption_Multiplication_Test3(){
        SplitAlgorithmEncryption SplitAlgorithmEncryption = new SplitAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmEncryption.SplitAlgorithm(key, byteBuffer, 3);
        assertNotNull(ans);
        byte[] wantedResult = {15,40,50,60};
        assertArrayEquals(wantedResult, ans);
    }


    @Test
    public void SplitAlgorithmEncryption_runAlgorithm_Encryption_caesar_Test3(){
        SplitAlgorithmEncryption SplitAlgorithmEncryption = new SplitAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmEncryption.runAlgorithm(1, key, key.firstKey, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {8,13,15,17};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void SplitAlgorithmEncryption_runAlgorithm_Encryption_XOR_Test4(){
        SplitAlgorithmEncryption SplitAlgorithmEncryption = new SplitAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmEncryption.runAlgorithm(2, key, key.firstKey, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {6,13,15,9};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void SplitAlgorithmEncryption_runAlgorithm_Encryption_Multiplication_Test5(){
        SplitAlgorithmEncryption SplitAlgorithmEncryption = new SplitAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        key.secondKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmEncryption.runAlgorithm(3, key, key.firstKey, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {15,40,50,60};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void SplitAlgorithmEncryption_SplitBufferOdd_EncryptionTest6(){
        SplitAlgorithmEncryption SplitAlgorithmEncryption = new SplitAlgorithmEncryption();
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmEncryption.SplitBufferOdd(byteBuffer);
        assertNotNull(ans);
        byte[] expected = {8,12};
        assertArrayEquals(expected, ans);
    }

    @Test
    public void SplitAlgorithmEncryption_SplitBufferEven_EncryptionTest7(){
        SplitAlgorithmEncryption SplitAlgorithmEncryption = new SplitAlgorithmEncryption();
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = SplitAlgorithmEncryption.SplitBufferEven(byteBuffer);
        assertNotNull(ans);
        byte[] expected = {3,10};
        assertArrayEquals(expected, ans);
    }

    @Test
    public void SplitAlgorithmEncryption_SplitBufferEven_EncryptionTest8(){
        SplitAlgorithmEncryption SplitAlgorithmEncryption = new SplitAlgorithmEncryption();
        byte[] byteBuffer = {3,8,10,12,14};
        byte[] ans = SplitAlgorithmEncryption.SplitBufferEven(byteBuffer);
        assertNotNull(ans);
        byte[] expected = {3,10,14};
        assertArrayEquals(expected, ans);
    }
}