import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Elior on 2016-08-06.
 */
public @Data class XORAlgorithmDecryptionTest {
    @Test
    public void XORAlgorithmDecryptionTest1(){
        XORAlgorithmDecryption XORAlgorithmDecryption = new XORAlgorithmDecryption();
        byte key = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = XORAlgorithmDecryption.XORAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {6,13,15,9};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void XORAlgorithmDecryptionTest2(){
        XORAlgorithmDecryption XORAlgorithmDecryption = new XORAlgorithmDecryption();
        byte key = 0;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = XORAlgorithmDecryption.XORAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {3,8,10,12};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void XORAlgorithmDecryptionTest3(){
        XORAlgorithmDecryption XORAlgorithmDecryption = new XORAlgorithmDecryption();
        byte key = 50;
        byte[] byteBuffer = {3,8,10,127};
        byte[] ans = XORAlgorithmDecryption.XORAlgorithm(key, byteBuffer);
        assertNotNull(ans);
        byte[] wantedResult = {49,58,56,77};
        assertArrayEquals(wantedResult, ans);
    }
}