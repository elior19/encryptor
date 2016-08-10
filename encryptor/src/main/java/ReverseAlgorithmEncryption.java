
import lombok.Data;

import java.util.Scanner;

/**
 * Created by Elior on 2016-08-03.
 */
public @Data class ReverseAlgorithmEncryption extends Encryption {

    public static int GetUsersAnswer(){
        //get users answer
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        //catch the first enter button
        String enter = scanner.nextLine();
        return number;
    }

    public int PrintAlgorithmsOptions(){
        System.out.println("Please choose another encryption algorithm to be reversed (Press a number)");
        System.out.println("1 - Caesar Algorithm");
        System.out.println("2 - XOR Algorithm");
        System.out.println("3 - Multiplication Algorithm");

        return GetUsersAnswer();
    }

    public int returnSecondAlgorithmNumber(){
        return PrintAlgorithmsOptions();
    }

    public byte[] ReverseAlgorithm(Key key, byte[] byteBuffer, int secondAlgorithmNumber) {
        byte[] ans = byteBuffer;

        switch (secondAlgorithmNumber){
            case 1:
                //1 - Caesar Algorithm
                caesarAlgorithmDecryption caesarAlgorithmDecryption = new caesarAlgorithmDecryption();
                ans = caesarAlgorithmDecryption.CaesarAlgorithm(key.firstKey, byteBuffer);
                break;
            case 2:
                //2 - XOR Algorithm
                XORAlgorithmDecryption XORAlgorithmDecryption = new XORAlgorithmDecryption();
                ans = XORAlgorithmDecryption.XORAlgorithm(key.firstKey, byteBuffer);
                break;
            case 3:
                //3 - Multiplication Algorithm
                MultiplicationAlgorithmDecryption MultiplicationAlgorithmDecryption = new MultiplicationAlgorithmDecryption();
                ans = MultiplicationAlgorithmDecryption.MultiplicationAlgorithm(key, 1, byteBuffer);
                break;
            default:
                System.out.println("Invalid algorithm");
                break;
        }

        return ans;
    }
}
