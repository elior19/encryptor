
import lombok.Data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Elior on 2016-08-01.
 */
public @Data class MultiplicationAlgorithmDecryption extends Encryption {
    private final byte max = Byte.MAX_VALUE;
    private final byte min = Byte.MIN_VALUE;

    private void checkKey(byte key){
        //check if the key is divided by 2 or zero and if so print relevant message
        if (key%2==0){
            System.out.println("The key you choose (" + key + ") has a number (byte) divided by 2 - You can loss data ");
        }
    }

    public byte findKey(byte key){
        checkKey(key);
        byte decryptionKey = 0;

        for(byte i=min; i<max; i++){
            if((byte) (i * key)==1){
                decryptionKey=i;
                break;
            }
        }

        return decryptionKey;
    }

    public Key checkKeyAndOverrideIfNecessary(Key key, int keyNum){
        //check if the key is divided by 2 or zero and if so increase the key to odd number instead of even one
        switch (keyNum){
            case 1:
                if (key.firstKey%2==0){
                    key.firstKey++;
                    System.out.println("Key was increased from even to odd number (byte) ");
                }
                break;
            case 2:
                if (key.secondKey%2==0){
                    key.secondKey++;
                    System.out.println("Key was increased from even to odd number (byte) ");
                }
                break;
            default:
                System.out.println("invalid option");
                break;
        }

        //override key.bin file
        try
        {
            FileOutputStream fileOut = new FileOutputStream("key.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(key);
            out.close();
            fileOut.close();
            System.out.println("Serialized data (key) is saved in \"key.bin\" ");
        } catch(IOException i) {
            i.printStackTrace();
        }

        return key;
    }

    public byte[] MultiplicationAlgorithm(Key key, int keyNum, byte[] byteBuffer) {

        //checkKey(key);
        key = checkKeyAndOverrideIfNecessary(key, keyNum);

        byte[] ans = byteBuffer;

        byte decryptionKey = 0;

        switch (keyNum){
            case 1:
                decryptionKey = findKey(key.firstKey);
                break;
            case 2:
                decryptionKey = findKey(key.secondKey);
                break;
            default:
                System.out.println("invalid option");
                break;
        }

        for(int i=0; i<byteBuffer.length; i++){
            //MWO = Multiplication With Overflow (applying overflow on the multiplication by using casting to byte)
            ans[i] = (byte) (byteBuffer[i] * decryptionKey);
        }

        return ans;
    }
}
