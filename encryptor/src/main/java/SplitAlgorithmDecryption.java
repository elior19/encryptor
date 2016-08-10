
import lombok.Data;

import java.util.Scanner;

/**
 * Created by Elior on 2016-08-04.
 */
public @Data class SplitAlgorithmDecryption  extends Decryption {

    public byte[] SplitBufferEven(byte[] byteBuffer){
        int size = byteBuffer.length;
        if(size%2==1){
            size++;
        }
        size=size/2;
        byte[] ans = new byte[size];
        for(int i=0; i<byteBuffer.length; i++){
            if(i%2==0){
                ans[i/2] = byteBuffer[i];
            }
        }
        return ans;
    }

    public byte[] SplitBufferOdd(byte[] byteBuffer){
        int size = (byteBuffer.length)/2;
        byte[] ans = new byte[size];
        for(int i=0; i<byteBuffer.length; i++){
            if(i%2!=0){
                ans[(i-1)/2] = byteBuffer[i];
            }
        }
        return ans;
    }

    private static int GetUsersAnswer(){
        //get users answer
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        //catch the first enter button
        String enter = scanner.nextLine();
        return number;
    }

    private int PrintAlgorithmsOptions(){
        System.out.println("Please choose the encryption algorithm (Press a number)");
        System.out.println("1 - Caesar Algorithm");
        System.out.println("2 - XOR Algorithm");
        System.out.println("3 - Multiplication Algorithm");

        return GetUsersAnswer();
    }

    public byte[] runAlgorithm(int AlgorithmNumber, Key ObjectKey, byte key, int keyNum, byte[] byteBuffer) {
        byte[] ans = byteBuffer;
        switch (AlgorithmNumber) {
            case 1:
                //1 - Caesar Algorithm
                caesarAlgorithmDecryption caesarAlgorithmDecryption = new caesarAlgorithmDecryption();
                ans = caesarAlgorithmDecryption.CaesarAlgorithm(key, byteBuffer);
                break;
            case 2:
                //2 - XOR Algorithm
                XORAlgorithmDecryption XORAlgorithmDecryption = new XORAlgorithmDecryption();
                ans = XORAlgorithmDecryption.XORAlgorithm(key, byteBuffer);
                break;
            case 3:
                //3 - Multiplication Algorithm
                MultiplicationAlgorithmDecryption MultiplicationAlgorithmDecryption = new MultiplicationAlgorithmDecryption();
                ans = MultiplicationAlgorithmDecryption.MultiplicationAlgorithm(ObjectKey, keyNum, byteBuffer);
                break;
            default:
                System.out.println("Invalid algorithm");
                break;
        }
        return ans;
    }

    public int returnSecondAlgorithmNumber(){
        return PrintAlgorithmsOptions();
    }

    public byte[] SplitAlgorithm(Key key, byte[] byteBuffer, int SecondAlgorithmNumber){
        byte[] ans = byteBuffer;
        byte[] even = SplitBufferEven(byteBuffer); //z
        byte[] odd = SplitBufferOdd(byteBuffer);

        //let the user chose the algorithm
        //int SecondAlgorithmNumber = PrintAlgorithmsOptions();

        //the result of the first algorithm on the evens
        even = runAlgorithm(SecondAlgorithmNumber, key, key.secondKey, 2, even);

        //the result of the first algorithm on the odds
        odd = runAlgorithm(SecondAlgorithmNumber, key, key.firstKey, 1, odd);

        //merge the buffers
        int j = 0, k=0;
        for(int i=0; i<byteBuffer.length; i++){
            if(i%2==0){
                ans[i] = even[j];
                j++;
            }
            else {
                ans[i] = odd[k];
                k++;
            }
        }

        return ans;
    }
}