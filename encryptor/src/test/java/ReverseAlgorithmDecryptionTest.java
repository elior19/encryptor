import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Elior on 2016-08-07.
 */
public @Data class ReverseAlgorithmDecryptionTest {
    @Test
    public void ReverseAlgorithmDecryption_caesar_Test1(){
        ReverseAlgorithmDecryption ReverseAlgorithmDecryption = new ReverseAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = ReverseAlgorithmDecryption.ReverseAlgorithm(key, byteBuffer, 1);
        assertNotNull(ans);
        byte[] wantedResult = {8,13,15,17};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void ReverseAlgorithmDecryption_XOR_Test2(){
        ReverseAlgorithmDecryption ReverseAlgorithmDecryption = new ReverseAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        byte[] byteBuffer = {3,8,10,12};
        byte[] ans = ReverseAlgorithmDecryption.ReverseAlgorithm(key, byteBuffer, 2);
        assertNotNull(ans);
        byte[] wantedResult = {6,13,15,9};
        assertArrayEquals(wantedResult, ans);
    }

    @Test
    public void ReverseAlgorithmDecryption_Multiplication_Test3(){
        ReverseAlgorithmDecryption ReverseAlgorithmDecryption = new ReverseAlgorithmDecryption();
        Key key = new Key();
        key.firstKey = 5;
        byte[] byteBuffer = {3,8,10,127};
        byte[] ans = ReverseAlgorithmDecryption.ReverseAlgorithm(key, byteBuffer, 3);
        assertNotNull(ans);
        byte[] wantedResult = {15,40,50,123};
        assertArrayEquals(wantedResult, ans);
    }


}