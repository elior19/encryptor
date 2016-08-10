import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.*;

//import static org.mockito.Mockito.*;

/**
 * Created by Elior on 2016-08-06.
 */
public @Data class MultiplicationAlgorithmEncryptionTest {

    @Test
    public void MultiplicationAlgorithmEncryptionTest1(){
        MultiplicationAlgorithmEncryption MultiplicationAlgorithmEncryption = new MultiplicationAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = MultiplicationAlgorithmEncryption.MultiplicationAlgorithm(key, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {15,40,50,60};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void MultiplicationAlgorithmEncryptionTest2(){
        MultiplicationAlgorithmEncryption MultiplicationAlgorithmEncryption = new MultiplicationAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 0;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = MultiplicationAlgorithmEncryption.MultiplicationAlgorithm(key, 1, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {3,8,10,12};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void MultiplicationAlgorithmEncryptionTest3(){
        MultiplicationAlgorithmEncryption MultiplicationAlgorithmEncryption = new MultiplicationAlgorithmEncryption();
        Key key = new Key();
        key.secondKey = 50;
        byte[] byteBuffer = {3,8,10,127};
        byte[] ans = MultiplicationAlgorithmEncryption.MultiplicationAlgorithm(key, 2, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {-103,-104,-2,77};
        assertArrayEquals(wantedResult, ans);
    }


    @Test
    public void MultiplicationAlgorithm_checkKeyAndOverrideIfNecessary_EncryptionTest3(){
        Key keyMock = new Key((byte)1, (byte)1);
        MultiplicationAlgorithmEncryption MultiplicationAlgorithmEncryption = new MultiplicationAlgorithmEncryption();
        keyMock.firstKey = 2;
        Key ans = MultiplicationAlgorithmEncryption.checkKeyAndOverrideIfNecessary(keyMock, 1);
        assertNotNull(ans);
        Key expected = new Key((byte)3, (byte)1);
        assertEquals(expected, ans);
    }

    @Test
    public void MultiplicationAlgorithm_checkKeyAndOverrideIfNecessary_EncryptionTest4(){
        Key keyMock = new Key((byte)1, (byte)1);
        MultiplicationAlgorithmEncryption MultiplicationAlgorithmEncryption = new MultiplicationAlgorithmEncryption();
        keyMock.secondKey = 50;
        Key ans = MultiplicationAlgorithmEncryption.checkKeyAndOverrideIfNecessary(keyMock, 2);
        assertNotNull(ans);
        Key expected = new Key((byte)1, (byte)51);
        assertEquals(expected, ans);
    }
}