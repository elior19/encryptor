
import lombok.Data;

import java.util.Scanner;

/**
 * Created by Elior on 2016-08-03.
 */
public @Data class DoubleAlgorithmEncryption extends Encryption{

    private static int GetUsersAnswer(){
        //get users answer
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        //catch the first enter button
        String enter = scanner.nextLine();
        return number;
    }

    private int PrintAlgorithmsOptions(String numberAlg){
        System.out.println("Please choose the " + numberAlg + " encryption algorithm (Press a number)");
        System.out.println("1 - Caesar Algorithm");
        System.out.println("2 - XOR Algorithm");
        System.out.println("3 - Multiplication Algorithm");

        return GetUsersAnswer();
    }

    public byte[] runAlgorithm(int AlgorithmNumber, Key ObjectKey, byte key, int keyNum, byte[] byteBuffer) {
        byte[] ans = byteBuffer;

        switch (AlgorithmNumber){
            case 1:
                //1 - Caesar Algorithm
                caesarAlgorithmEncryption caesarAlgorithmEncryption = new caesarAlgorithmEncryption();
                ans = caesarAlgorithmEncryption.CaesarAlgorithm(key, byteBuffer);
                break;
            case 2:
                //2 - XOR Algorithm
                XORAlgorithmEncryption XORAlgorithmEncryption = new XORAlgorithmEncryption();
                ans = XORAlgorithmEncryption.XORAlgorithm(key, byteBuffer);
                break;
            case 3:
                //3 - Multiplication Algorithm
                MultiplicationAlgorithmEncryption MultiplicationAlgorithmEncryption = new MultiplicationAlgorithmEncryption();
                ans = MultiplicationAlgorithmEncryption.MultiplicationAlgorithm(ObjectKey, keyNum, byteBuffer);
                break;
            default:
                System.out.println("Invalid algorithm");
                break;
        }
        return ans;
    }

    public int returnFirstAlgorithmNumber(){
        return PrintAlgorithmsOptions("first");
    }

    public int returnSecondAlgorithmNumber(){
        return PrintAlgorithmsOptions("second");
    }

    public byte[] DoubleAlgorithm(Key key, byte[] byteBuffer, int firstAlgorithmNumber, int secondAlgorithmNumber) {

        byte[] ans = byteBuffer;

        //let the user chose the first algorithm
        //int firstAlgorithmNumber = PrintAlgorithmsOptions("first");

        //the result of the first algorithm
        ans = runAlgorithm(firstAlgorithmNumber, key, key.firstKey, 1, byteBuffer);

        //let the user chose the second algorithm
        //int secondAlgorithmNumber = PrintAlgorithmsOptions("second");

        //the result after the second algorithm
        ans = runAlgorithm(secondAlgorithmNumber, key, key.secondKey, 2, ans);

        return ans;
    }
}
