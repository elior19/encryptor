import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Elior on 2016-08-07.
 */
public @Data class ReverseAlgorithmEncryptionTest {
    @Test
    public void ReverseAlgorithmEncryption_caesar_Test1(){
        ReverseAlgorithmEncryption ReverseAlgorithmEncryption = new ReverseAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = ReverseAlgorithmEncryption.ReverseAlgorithm(key, byteBuffer, 1);
        assertNotNull(ans);
        byte[] wantedResult = {-2,3,5,7};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void ReverseAlgorithmEncryption_XOR_Test2(){
        ReverseAlgorithmEncryption ReverseAlgorithmEncryption = new ReverseAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = ReverseAlgorithmEncryption.ReverseAlgorithm(key, byteBuffer, 2);
        assertNotNull(ans);
        byte[] wantedResult = {6,13,15,9};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void ReverseAlgorithmEncryption_Multiplication_Test3(){
        ReverseAlgorithmEncryption ReverseAlgorithmEncryption = new ReverseAlgorithmEncryption();
        Key key = new Key();
        key.firstKey = 5;
        byte[] byteBuffer = {3,8,10,127};
        byte[] ans = ReverseAlgorithmEncryption.ReverseAlgorithm(key, byteBuffer, 3);
        assertNotNull(ans);
        byte[] wantedResult = {103,104,2,-77};
        assertArrayEquals(wantedResult, ans);
    }


}