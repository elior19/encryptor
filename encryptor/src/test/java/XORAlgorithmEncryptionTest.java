import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Elior on 2016-08-06.
 */
public @Data class XORAlgorithmEncryptionTest {
    @Test
    public void XORAlgorithmEncryptionTest1(){
        XORAlgorithmEncryption XORAlgorithmEncryption = new XORAlgorithmEncryption();
        byte key = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = XORAlgorithmEncryption.XORAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {6,13,15,9};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void XORAlgorithmEncryptionTest2(){
        XORAlgorithmEncryption XORAlgorithmEncryption = new XORAlgorithmEncryption();
        byte key = 0;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = XORAlgorithmEncryption.XORAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {3,8,10,12};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void XORAlgorithmEncryptionTest3(){
        XORAlgorithmEncryption XORAlgorithmEncryption = new XORAlgorithmEncryption();
        byte key = 50;
        byte[] byteBuffer = {3,8,10,127};
        byte[] ans = XORAlgorithmEncryption.XORAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {49,58,56,77};
        assertArrayEquals(wantedResult, ans);
    }
}