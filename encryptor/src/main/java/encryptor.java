
import lombok.Data;

import java.io.File;
import java.util.Scanner;

//import org.apache.log4j.Logger;

/**
 * Created by Elior on 2016-07-26.
 */


public @Data class encryptor {

    private static void PrintMenu(){
        //show menu to the user
        System.out.println("Please choose an action: (press a number)");
        System.out.println("1 - Encryption");
        System.out.println("2 - Decryption");
    }

    private static void PrintOptionsOfEncryptionAndDecryption(){
        //show menu to the user
        System.out.println("Do you want to encrypt/decrypt (press a number)");
        System.out.println("1 - a file");
        System.out.println("2 - an entire directory");
    }

    private static void PrintOptionsOfSynocOrAsync(){
        //show menu to the user
        System.out.println("What do you prefer (press a number)");
        System.out.println("1 - sync = All files are encrypted in one thread.");
        System.out.println("2 - Async = Files are encrypted in threads.");
    }

    private static int GetUsersAnswer(){
        //get users answer
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        //catch the first enter button
        String enter = scanner.nextLine();
        return number;
    }

    private static void printUserPreference(int encOrDec, String path){
        //print user preference
        if (encOrDec == 1) { //Encryption
            System.out.println("encryption simulation of file " + path);
        } else { //Decryption
            System.out.println("decryption simulation of file " + path);
        }
    }

    private static String AskForFileAndPrintMessage(int encOrDec, int fileOrDirectory){
        String path = "";
        boolean foundFile = false;
        while(!foundFile) {
            //ask for file
            System.out.println("Please enter a path");
            Scanner scanner = new Scanner(System.in);
            path = scanner.nextLine();

            printUserPreference(encOrDec, path);

            //looking for the path with try-catch - the old way
           /* try {
                File file = new File(path);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                foundFile = true;
            } catch (FileNotFoundException e) {
                System.out.println("Exception: " + path + " not found");
            } /*catch (IOException e) {
                System.out.println("file " + path + " not found");
            }*/

            //looking for the path
            File file = new File(path);
            if(fileOrDirectory==2 && !file.isDirectory()){
                System.out.println("Exception: " + path + " is not a directory");
                continue;
            }
            if(fileOrDirectory==1 && file.isDirectory()){
                System.out.println("Exception: " + path + " is a directory and not a file");
                continue;
            }
            if(file.exists()){
                foundFile = true;
            }
            else{
                System.out.println("Exception: " + path + " not found");
            }

        }
        return path;
    }

    private static void PrintAlgorithmsOptions(){
        System.out.println("Please choose an encryption algorithm (Press a number)");
        System.out.println("1 - Caesar Algorithm");
        System.out.println("2 - XOR Algorithm");
        System.out.println("3 - Multiplication Algorithm");
        System.out.println("4 - Double Algorithm");
        System.out.println("5 - Reverse Algorithm");
        System.out.println("6 - Split Algorithm");
    }

    private static boolean checkOption(int optionNum){
        boolean ans = true;
        if(optionNum!=1 && optionNum!=2){
            try {
                throw new Exception("Option " + optionNum + " Not Valid");
            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println("Option " + optionNum + " Not Valid");
                ans = false;
            }
        }
        return ans;
    }

    public static int loadXMLAnswer(){
        int ans = 0;

        XMLReader XMLReader = new XMLReader();
        ans = XMLReader.returnAlgNum();

        return ans;
    }

    public static void changeCurrentAlgorithm(int usersAns){
        XMLCreator XMLCreator = new XMLCreator();
        XMLCreator.createFile(usersAns);
    }

    private static void useNotDefaultPrintOptions(){
        //show menu to the user
        System.out.println("The current encryption algorithm is caesar algorithm. Do you want to *use* it? (press a number)");
        System.out.println("1 - yes");
        System.out.println("2 - no");
    }

    public static int wantToUseNotDefaultOrNot(){
        useNotDefaultPrintOptions();
        int defaultAlg = GetUsersAnswer();
        int AlgorithmNumber = 0;
        switch (defaultAlg){
            case 1: //yes
                AlgorithmNumber = loadXMLAnswer();
                break;
            case 2: //no
                PrintAlgorithmsOptions();
                AlgorithmNumber = GetUsersAnswer();
                break;
            default:
                System.out.println("Not valid option");
                break;
        }
        return AlgorithmNumber;
    }

    private static void changDefaultPrintOptions(){
        //show menu to the user
        System.out.println("The current encryption algorithm is caesar algorithm. Do you want to *change* it? (press a number)");
        System.out.println("1 - yes");
        System.out.println("2 - no");
    }

    public static int changeDefaultOrNot(int AlgorithmNumber){
        changDefaultPrintOptions();
        int changeAlg = GetUsersAnswer();
        switch (changeAlg){
            case 1: //yes
                PrintAlgorithmsOptions();
                changeCurrentAlgorithm(GetUsersAnswer());
                AlgorithmNumber = loadXMLAnswer();
                break;
            case 2: //no
                break;
            default:
                System.out.println("Not valid option");
                break;
        }
        return AlgorithmNumber;
    }

    public static void main(String[] args){

        PrintOptionsOfEncryptionAndDecryption();
        int fileOrDirectory = GetUsersAnswer(); // 1-file   2-directory
        if(!checkOption(fileOrDirectory)){
            return;
        }

        //sync Or Async for directory only (files are only sync)
        int syncOrAsync = 1;
        if(fileOrDirectory==2){
            PrintOptionsOfSynocOrAsync();
            syncOrAsync = GetUsersAnswer(); //1-sync 2-Async
        }
        if(!checkOption(syncOrAsync)){
            return;
        }

        PrintMenu();
        int encOrDec = GetUsersAnswer(); //1 - Encryption    2 - Decryption
        if(!checkOption(encOrDec)){
            return;
        }

        String path = AskForFileAndPrintMessage(encOrDec, fileOrDirectory);

        /*the old version - before xml
        PrintAlgorithmsOptions();
        int AlgorithmNumber = GetUsersAnswer();*/

        /*the old version - before adding options
        PrintAlgorithmsOptions();
        changeCurrentAlgorithm(GetUsersAnswer());
        int AlgorithmNumber = loadXMLAnswer();*/

        int AlgorithmNumber = wantToUseNotDefaultOrNot();
        AlgorithmNumber = changeDefaultOrNot(AlgorithmNumber);


        if (encOrDec == 1) { //Encryption
            Encryption Encryption = new Encryption();
            Encryption.setPath(path);
            Encryption.setAlgorithmNumber(AlgorithmNumber);
            Encryption.setFileOrDir(fileOrDirectory);
            Encryption.setSyncOrAsync(syncOrAsync);
            Encryption.Run();
        } else if(encOrDec == 2){ //Decryption
            Decryption Decryption = new Decryption();
            Decryption.setPath(path);
            Decryption.setAlgorithmNumber(AlgorithmNumber);
            Decryption.setFileOrDir(fileOrDirectory);
            Decryption.setSyncOrAsync(syncOrAsync);
            Decryption.Run();
        }
    }
}
