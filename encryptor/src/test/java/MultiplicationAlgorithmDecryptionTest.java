import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Elior on 2016-08-07.
 */
public @Data class MultiplicationAlgorithmDecryptionTest {
    @Test
    public void MultiplicationAlgorithmDecryptionTest1(){
        MultiplicationAlgorithmDecryption MultiplicationAlgorithmDecryption = new MultiplicationAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = MultiplicationAlgorithmDecryption.MultiplicationAlgorithm(key, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {103,104,2,-100};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void MultiplicationAlgorithmDecryptionTest2(){
        MultiplicationAlgorithmDecryption MultiplicationAlgorithmDecryption = new MultiplicationAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 0;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = MultiplicationAlgorithmDecryption.MultiplicationAlgorithm(key, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {3,8,10,12};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void MultiplicationAlgorithmDecryptionTest3(){
        MultiplicationAlgorithmDecryption MultiplicationAlgorithmDecryption = new MultiplicationAlgorithmDecryption();
        Key key = new Key();
        key.secondKey = 50;
        byte[] byteBuffer = {3,8,10,127};
        byte[] ans = MultiplicationAlgorithmDecryption.MultiplicationAlgorithm(key, 2, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {-15,-40,-50,-123};
        assertArrayEquals(wantedResult, ans);
    }


    @Test
    public void MultiplicationAlgorithm_checkKeyAndOverrideIfNecessary_DecryptionTest3(){
        Key keyMock = new Key((byte)1, (byte)1);
        MultiplicationAlgorithmDecryption MultiplicationAlgorithmDecryption = new MultiplicationAlgorithmDecryption();
        keyMock.firstKey = 2;
        Key ans = MultiplicationAlgorithmDecryption.checkKeyAndOverrideIfNecessary(keyMock, 1);
        assertNotNull(ans);
        Key expected = new Key((byte)3, (byte)1);
        assertEquals(expected, ans);
    }

    @Test
    public void MultiplicationAlgorithm_checkKeyAndOverrideIfNecessary_DecryptionTest4(){
        Key keyMock = new Key((byte)1, (byte)1);
        MultiplicationAlgorithmDecryption MultiplicationAlgorithmDecryption = new MultiplicationAlgorithmDecryption();
        keyMock.secondKey = 50;
        Key ans = MultiplicationAlgorithmDecryption.checkKeyAndOverrideIfNecessary(keyMock, 2);
        assertNotNull(ans);
        Key expected = new Key((byte)1, (byte)51);
        assertEquals(expected, ans);
    }

    @Test
    public void MultiplicationAlgorithm_findKey_DecryptionTest5(){
        MultiplicationAlgorithmDecryption MultiplicationAlgorithmDecryption = new MultiplicationAlgorithmDecryption();
        byte key = 5;
        byte ans = MultiplicationAlgorithmDecryption.findKey(key);
        assertNotNull(ans);
        byte expected = -51;
        assertEquals(expected, ans);
    }

    @Test
    public void MultiplicationAlgorithm_findKey_DecryptionTest6(){
        MultiplicationAlgorithmDecryption MultiplicationAlgorithmDecryption = new MultiplicationAlgorithmDecryption();
        byte key = 50;
        byte ans = MultiplicationAlgorithmDecryption.findKey(key);
        assertNotNull(ans);
        byte expected = 0;
        assertEquals(expected, ans);
    }
}