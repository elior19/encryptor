
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Elior on 2016-07-31.
 */
public @Data class Decryption implements TakeAction {

    private final byte max = Byte.MAX_VALUE;
    private final byte min = Byte.MIN_VALUE;

    public static Statistics statistics = new Statistics();

    public LoggerData loggerData = new LoggerData();

    public Object lock = new Object();
    public static Boolean isWakeupNeeded = false;

    protected Key key = new Key();
    public String path;
    public int fileOrDir; // 1-file 2-Dir
    public int syncOrAsync = 1; //1-sync 2-Async
    protected byte[] byteBuffer;
    public int algorithmNumber;
    public static int firstAlgorithmNumber;
    public static int secondAlgorithmNumber;
    public int state = 0; // 1 = start 0 = end
    private List<Observer> observers = new ArrayList<Observer>();



    public void addObserver(Observer toAdd) {
        observers.add(toAdd);
    }

    public void removeObserver(Observer toRemove) {
        observers.remove(toRemove);
    }

    // Someone who change state
    public void changeState(long startTime, long endTime) {
        state = 1 - state;

        // Notify everybody that may be interested.
        for (Observer ob : observers) {
            ob.someoneChangedState(state);

            if(state==0){ //It's end event
                ob.calculateAndPrintRunTime(startTime, endTime);
            }
        }
    }

    public void HandelKey() {
      /*  //ask for key - the old way - only one key
        System.out.println("Please enter a Byte key ");
        Scanner scanner = new Scanner(System.in);
        try {
            key = scanner.nextByte();
        }
        catch (Exception e){
            System.out.println("Exception: key is not a byte");
        } */

        //ask for key
        System.out.println("Please enter the path of the key file");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            key = (Key) in.readObject();
            in.close();
            fileIn.close();
        } catch(IOException i) {
            i.printStackTrace();
            statistics.status_succ = false;
            statistics.exception = i;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Decryption of file: " + path + " because: " + i.getClass().toString());
        } catch(ClassNotFoundException c) {
            System.out.println("Key class not found");
            c.printStackTrace();
            statistics.status_succ = false;
            statistics.exception = c;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Decryption of file: " + path + " because: " + c.getClass().toString());
        } catch (Exception e){
            e.printStackTrace();
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Decryption of file: " + path + " because: " + e.getClass().toString());
        }
        finally {
            statistics.file = new File (path);
            createXMLReports(statistics);
        }
    }

    public byte[] ReadFileContent(String path){
        byte[] byteBuffer = null;
        //open file
        try {
            //open file for read only
            RandomAccessFile file = new RandomAccessFile(path, "r");
            //create byte array
            byteBuffer = new byte[(int)file.length()];
            //read the file to the buffer
            file.readFully(byteBuffer);
        } catch (FileNotFoundException e) {
            System.out.println("Exception: file " + path + " not found");
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.exception = e;
            loggerData.addToLog("Failed Decryption of file: " + path + " because: " + e.getClass().toString());
        } catch (IOException e) {
            e.printStackTrace();
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.exception = e;
            loggerData.addToLog("Failed Decryption of file: " + path + " because: " + e.getClass().toString());
        } catch (Exception e){
            e.printStackTrace();
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Decryption of file: " + path + " because: " + e.getClass().toString());
        }
        finally {
            statistics.file = new File (path);
            createXMLReports(statistics);
        }
        return byteBuffer;
    }

    public void GenerateNewFileAndPrintToIt(byte[] ans, String fileInDirectoryName, String path, int fileOrDir) {
        //generate new file

        String newFileName = "";

        switch (fileOrDir){
            case 1:
                //file
                int position = path.lastIndexOf('.');
                String extension = path.substring(position);
                //remove the ".encrypted" extension if needed
                if (extension.equals(".encrypted")){
                    path = path.substring(0,position);
                    position = path.lastIndexOf('.');
                    extension = path.substring(position);
                }

                String originalName = path.substring(0,position);
                newFileName = originalName + "_decrypted" + extension;
                break;
            case  2:
                //directory
                newFileName = path + "/decrypted/" + fileInDirectoryName;
                break;
            default:
                System.out.println("invalid option");
                break;
        }


        //print output to new file
        try{
            FileOutputStream fos = new FileOutputStream(newFileName);
            fos.write(ans);
        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + newFileName + " not found");
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Decryption of file: " + path + " because: " + e.getClass().toString());
        } catch (IOException e) {
            e.printStackTrace();
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Decryption of file: " + path + " because: " + e.getClass().toString());
        } catch (Exception e){
            e.printStackTrace();
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Decryption of file: " + path + " because: " + e.getClass().toString());
        }
        finally {
            statistics.file = new File (path);
            createXMLReports(statistics);
        }
    }

    public byte[] runTheAlgorithm(byte[] byteBuffer, int algorithmNumber, Key key, boolean choose) {
        byte[] ans = null;
        //call to encryption method
        switch (algorithmNumber){
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
            case 4:
                //4 - Double Algorithm
                DoubleAlgorithmDecryption DoubleAlgorithmDecryption = new DoubleAlgorithmDecryption();
                if (choose){
                    firstAlgorithmNumber = DoubleAlgorithmDecryption.returnFirstAlgorithmNumber();
                    secondAlgorithmNumber = DoubleAlgorithmDecryption.returnSecondAlgorithmNumber();
                    synchronized (lock) {
                        isWakeupNeeded = true;
                        lock.notifyAll();
                    }
                }
                ans = DoubleAlgorithmDecryption.DoubleAlgorithm(key, byteBuffer, firstAlgorithmNumber, secondAlgorithmNumber);
                choose = false;
                break;
            case 5:
                //5 - Reverse Algorithm
                ReverseAlgorithmDecryption ReverseAlgorithmDecryption = new ReverseAlgorithmDecryption();
                if (choose){
                    secondAlgorithmNumber = ReverseAlgorithmDecryption.returnSecondAlgorithmNumber();
                    synchronized (lock) {
                        isWakeupNeeded = true;
                        lock.notifyAll();
                    }
                }
                ans = ReverseAlgorithmDecryption.ReverseAlgorithm(key, byteBuffer, secondAlgorithmNumber);
                choose = false;
                break;
            case 6:
                //6 - Split Algorithm
                SplitAlgorithmDecryption SplitAlgorithmDecryption = new SplitAlgorithmDecryption();
                if (choose){
                    secondAlgorithmNumber = SplitAlgorithmDecryption.returnSecondAlgorithmNumber();
                    synchronized (lock) {
                        isWakeupNeeded = true;
                        lock.notifyAll();
                    }
                }
                ans = SplitAlgorithmDecryption.SplitAlgorithm(key, byteBuffer, secondAlgorithmNumber);
                choose = false;
                break;
            default:
                System.out.println("Invalid algorithm");
                break;
        }
        return ans;
    }

    public static void createXMLReports(Statistics statistics){
        String fileNameString=statistics.file.getName();
        String encOrDecString="Decryption";
        String statusString=statistics.status_succ_ToString();
        String timeString=statistics.processTimeToString();
        String Exception_nameString = "";
        String Exception_massageString = "";
        String Exception_StacktraceString = "";
        if(!statistics.status_succ) {
            Exception_nameString = statistics.exception.getClass().getName();
            Exception_massageString = statistics.exception.getMessage();
            Exception_StacktraceString = statistics.exception.getStackTrace().toString();
        }


        XMLReportCreator XMLReportCreator = new XMLReportCreator();

        XMLReportCreator.createFile(fileNameString, encOrDecString, statusString, timeString, Exception_nameString, Exception_massageString, Exception_StacktraceString);
    }

    public void Run(){

        observerDecryption observerDecryption = new observerDecryption();
        addObserver(observerDecryption);

        //remember start time
        final long startTime = System.currentTimeMillis();

        //state = 1 = start
        changeState(startTime, startTime);

        HandelKey();
        byte[] ans = null;

        if(fileOrDir==1) {
            //file
            loggerData.addToLog("START Decryption for file: " + path);
            byteBuffer = ReadFileContent(path);
            ans = runTheAlgorithm(byteBuffer, algorithmNumber, key, true);
            GenerateNewFileAndPrintToIt(ans, "", path, 1);
            statistics.endTime = System.currentTimeMillis();
            if(statistics.status_succ) {
                loggerData.addToLog("End Decryption of file: " + path + " - The time it took: " + statistics.processTimeToString());
            }
            statistics.file = new File(path);
            createXMLReports(statistics);
        }
        else {
            //directory

            //check if the directory inside the encrypted folder and fix it if needed
            String pathWithEncFolder = path;
            try {
                int position = path.lastIndexOf('\\');
                String extension = path.substring(position);
                //remove the "\encrypted" folder if needed
                if (extension.equals("\\encrypted")) {
                    path = path.substring(0, position);
                }
            } catch (Exception e){
                e.printStackTrace();
                statistics.status_succ = false;
                statistics.exception = e;
                statistics.file = new File (path);
                loggerData.addToLog("Failed Decryption of file: " + path + " because: " + e.getClass().toString());
            }
            finally {
                statistics.file = new File (path);
                createXMLReports(statistics);
            }

            //create new folder for decrypted
            File dir = new File(path + "/decrypted");
            dir.mkdir();

            if (syncOrAsync == 1) {
                //sync
                //decrypt all files
                File[] listOfFiles = (new File(pathWithEncFolder)).listFiles();
                boolean choose = true;
                for (File file : listOfFiles) {
                    statistics.startTime = System.currentTimeMillis();
                    String fileName = file.getName();
                    if(!(fileName.equals("key.bin")) && !(fileName.equals("encrypted")) && !(fileName.equals("decrypted")) &&!(file.isDirectory())) {
                        loggerData.addToLog("START Decryption for file: " + file.getName());
                        ans = runTheAlgorithm(ReadFileContent(file.getPath()),algorithmNumber, key, choose);
                        GenerateNewFileAndPrintToIt(ans, file.getName(), path, 2);
                        choose = false;
                        statistics.endTime = System.currentTimeMillis();
                        if(statistics.status_succ) {
                            loggerData.addToLog("End Decryption of file: " + path + " - The time it took: " + statistics.processTimeToString());
                        }
                        statistics.file = file;
                        createXMLReports(statistics);
                    }
                }
            }
            else {
                //Async
                File[] listOfFiles = (new File(pathWithEncFolder)).listFiles();
                List<Thread> threads = new ArrayList<Thread>();
                boolean choose = true;
                for (File file : listOfFiles) {
                    statistics.startTime = System.currentTimeMillis();
                    String fileName = file.getName();
                    if(!(fileName.equals("key.bin")) && !(fileName.equals("encrypted")) && !(fileName.equals("decrypted")) && !(file.isDirectory())) {
                        loggerData.addToLog("START Decryption for file: " + file.getName());
                        Runnable threadJob = new FileThreadDecryption(key, path, algorithmNumber, file, choose, isWakeupNeeded);
                        Thread myThread = new Thread(threadJob);
                        myThread.setName(fileName);
                        threads.add(myThread);
                        myThread.start();
                        if(4 <= algorithmNumber && algorithmNumber<= 6) {
                            choose = false;
                        }
                        statistics.endTime = System.currentTimeMillis();
                        if(statistics.status_succ) {
                            loggerData.addToLog("End Decryption of file: " + path + " - The time it took: " + statistics.processTimeToString());
                        }
                        statistics.file = file;
                        createXMLReports(statistics);
                    }
                }
                //wait for all threads to finish
                for (Thread thread : threads) {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        statistics.status_succ = false;
                        statistics.exception = e;
                        statistics.file = new File (path);
                        loggerData.addToLog("Failed Decryption of file: " + path + " because: " + e.getClass().toString());
                    } catch (Exception e){
                        e.printStackTrace();
                        statistics.status_succ = false;
                        statistics.exception = e;
                        statistics.file = new File (path);
                        loggerData.addToLog("Failed Decryption of file: " + path + " because: " + e.getClass().toString());
                    }
                    finally {
                        statistics.file = new File (path);
                        createXMLReports(statistics);
                    }
                }
            }
        }

        //remember end time
        final long endTime = System.currentTimeMillis();

        //state = 0 = end
        changeState(startTime, endTime);
        removeObserver(observerDecryption);
    }
}
