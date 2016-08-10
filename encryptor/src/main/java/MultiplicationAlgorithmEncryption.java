
import lombok.Data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Elior on 2016-08-01.
 */
public @Data class MultiplicationAlgorithmEncryption extends Encryption {

   /*   //old method - for one key
        private byte checkKey(byte key){
        //check if the key is divided by 2 or zero and if so increase the key to odd number instead of even one
        if (key%2==0){
            key++;
            System.out.println("Key was increased from even to odd number (byte) - The new key is " + key);
        }
        return key;
    }*/

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
        byte[] ans = byteBuffer;

        key = checkKeyAndOverrideIfNecessary(key, keyNum);

        switch (keyNum){
            case 1:
                for(int i=0; i<byteBuffer.length; i++){
                    //MWO = Multiplication With Overflow (applying overflow on the multiplication by using casting to byte)
                    ans[i] = (byte) (byteBuffer[i] * key.firstKey);
                }
                 break;
            case 2:
                for(int i=0; i<byteBuffer.length; i++){
                    //MWO = Multiplication With Overflow (applying overflow on the multiplication by using casting to byte)
                    ans[i] = (byte) (byteBuffer[i] * key.secondKey);
                }
                break;
            default:
                System.out.println("invalid option");
                break;
        }

        return ans;
    }
}
