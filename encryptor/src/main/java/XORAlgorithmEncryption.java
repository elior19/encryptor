
import lombok.Data;

/**
 * Created by Elior on 2016-08-01.
 */
public @Data class XORAlgorithmEncryption extends Encryption {

    public byte[] XORAlgorithm(byte key, byte[] byteBuffer){
        byte[] ans = byteBuffer;

        for(int i=0; i<byteBuffer.length; i++){
            ans[i] = (byte) (byteBuffer[i] ^ key);
        }

        return ans;
    }
}
