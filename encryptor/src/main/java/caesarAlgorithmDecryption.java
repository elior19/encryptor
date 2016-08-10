
import lombok.Data;

/**
 * Created by Elior on 2016-08-01.
 */
public @Data class caesarAlgorithmDecryption extends Decryption {
    private final byte max = Byte.MAX_VALUE;
    private final byte min = Byte.MIN_VALUE;

    public byte[] CaesarAlgorithm(byte key, byte[] byteBuffer){
        byte[] ans = byteBuffer;

        for(int i=0; i<byteBuffer.length; i++){
            if(byteBuffer[i] + key <= max){
                ans[i] = (byte) (byteBuffer[i] - key);
            }
            else {
                //min - 1 = max
                ans[i] = (byte) ((byteBuffer[i] - key) - max + min - 1);
            }
        }

        return ans;
    }
}