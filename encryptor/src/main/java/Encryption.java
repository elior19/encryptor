/**
 * Created by Elior on 2016-07-31.
 */


import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public @Data class Encryption implements TakeAction{

    public static Statistics statistics = new Statistics();

    public LoggerData loggerData = new LoggerData();

    public Object lock = new Object();
    public static Boolean isWakeupNeeded = false;

    protected Key key = new Key();
    public String path;
    public int fileOrDir; // 1-file 2-Dir
    public int syncOrAsync = 1; //1-sync 2-Async
   // public File currFile;
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

            if (state == 0) { //It's end event
                ob.calculateAndPrintRunTime(startTime, endTime);
            }
        }
    }

    public void HandelKey(){
        //generate byte key
        Random random = new Random();
        byte[] bytes = new byte[2];
        random.nextBytes(bytes);
        byte tmp = bytes[0];
        key.firstKey = tmp;
        tmp = bytes[1];
        key.secondKey = tmp;

        //add the directory to the file name if needed
        String keyFileName = "";
        switch (fileOrDir){
            case 1:
                //file
                keyFileName = "key.bin";
                break;
            case 2:
                //directory
                keyFileName = path + "/key.bin";
                break;
            default:
                System.out.println("invalid option");
                break;
        }

        //print the key to a file named "key.bin" (in the directory if needed)
        try
        {
            FileOutputStream fileOut = new FileOutputStream(keyFileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(key);
            out.close();
            fileOut.close();
            System.out.println("Serialized data (key) is saved in \"" + keyFileName +"\" ");
        } catch(IOException i) {
            i.printStackTrace();
            statistics.status_succ = false;
            statistics.exception = i;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Encryption of file: " + path + " because: " + i.getClass().toString());
        } catch (Exception e){
            e.printStackTrace();
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Encryption of file: " + path + " because: " + e.getClass().toString());
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
            byteBuffer = new byte[(int) file.length()];
            //read the file to the buffer
            file.readFully(byteBuffer);
        } catch (FileNotFoundException e) {
            System.out.println("Exception: file " + path + " not found");
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Encryption of file: " + path + " because: " + e.getClass().toString());
        } catch (IOException e) {
            e.printStackTrace();
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Encryption of file: " + path + " because: " + e.getClass().toString());
        } catch (Exception e){
            e.printStackTrace();
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Encryption of file: " + path + " because: " + e.getClass().toString());
        }
        finally {
            statistics.file = new File (path);
            createXMLReports(statistics);
        }
        return byteBuffer;
    }

    public void GenerateNewFileAndPrintToIt(byte[] ans, String fileInDirectoryName, String path, int fileOrDir) {
        //handle file name
        String newFileName = "";
        switch (fileOrDir){
            case 1:
                //file
                newFileName = path + ".encrypted";
                break;
            case 2:
                //directory
                newFileName = path + "/encrypted/" + fileInDirectoryName;
                break;
            default:
                System.out.println("invalid option");
                break;
        }


        //generate new file and print output to it
        try {
            FileOutputStream fos = new FileOutputStream(newFileName);
            fos.write(ans);
        }catch (FileNotFoundException e) {
            System.out.println("Exception: file " + path + " not found");
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Encryption of file: " + path + " because: " + e.getClass().toString());
        }catch (IOException e) {
            e.printStackTrace();
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Encryption of file: " + path + " because: " + e.getClass().toString());
        } catch (Exception e){
            e.printStackTrace();
            statistics.status_succ = false;
            statistics.exception = e;
            statistics.file = new File (path);
            loggerData.addToLog("Failed Encryption of file: " + path + " because: " + e.getClass().toString());
        }
        finally {
            createXMLReports(statistics);
            statistics.file = new File (path);
        }

    }

    public byte[] runTheAlgorithm(byte[] byteBuffer, int algorithmNumber, Key key, boolean choose){
        byte[] ans = null;
        //call to encryption method
        switch (algorithmNumber){
            case 1:
                //1 - Caesar Algorithm
                caesarAlgorithmEncryption caesarAlgorithmEncryption = new caesarAlgorithmEncryption();
                ans = caesarAlgorithmEncryption.CaesarAlgorithm(key.firstKey, byteBuffer);
                break;
            case 2:
                //2 - XOR Algorithm
                XORAlgorithmEncryption XORAlgorithmEncryption = new XORAlgorithmEncryption();
                ans = XORAlgorithmEncryption.XORAlgorithm(key.firstKey, byteBuffer);
                break;
            case 3:
                //3 - Multiplication Algorithm
                MultiplicationAlgorithmEncryption MultiplicationAlgorithmEncryption = new MultiplicationAlgorithmEncryption();
                ans = MultiplicationAlgorithmEncryption.MultiplicationAlgorithm(key, 1, byteBuffer);
                break;
            case 4:
                //4 - Double Algorithm
                DoubleAlgorithmEncryption DoubleAlgorithmEncryption = new DoubleAlgorithmEncryption();
                if (choose){
                    firstAlgorithmNumber = DoubleAlgorithmEncryption.returnFirstAlgorithmNumber();
                    secondAlgorithmNumber = DoubleAlgorithmEncryption.returnSecondAlgorithmNumber();
                    synchronized (lock) {
                        isWakeupNeeded = true;
                        lock.notifyAll();
                    }
                }
                ans = DoubleAlgorithmEncryption.DoubleAlgorithm(key, byteBuffer, firstAlgorithmNumber, secondAlgorithmNumber);
                break;
            case 5:
                //5 - Reverse Algorithm
                ReverseAlgorithmEncryption ReverseAlgorithmEncryption = new ReverseAlgorithmEncryption();
                if (choose){
                    secondAlgorithmNumber = ReverseAlgorithmEncryption.returnSecondAlgorithmNumber();
                    synchronized (lock) {
                        isWakeupNeeded = true;
                        lock.notifyAll();
                    }
                }
                ans = ReverseAlgorithmEncryption.ReverseAlgorithm(key, byteBuffer, secondAlgorithmNumber);
                break;
            case 6:
                //6 - Split Algorithm
                SplitAlgorithmEncryption SplitAlgorithmEncryption = new SplitAlgorithmEncryption();
                if (choose){
                    secondAlgorithmNumber = SplitAlgorithmEncryption.returnSecondAlgorithmNumber();
                    synchronized (lock) {
                        isWakeupNeeded = true;
                        lock.notifyAll();
                    }
                }
                ans = SplitAlgorithmEncryption.SplitAlgorithm(key, byteBuffer, secondAlgorithmNumber);
                break;
            default:
                System.out.println("Invalid algorithm");
                break;
        }
        return ans;
    }

    public static void createXMLReports(Statistics statistics){
        String fileNameString=statistics.file.getName();
        String encOrDecString="Encryption";
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
        observerEncryption observerEncryption = new observerEncryption();
        addObserver(observerEncryption);

        //remember start time
        final long startTime = System.currentTimeMillis();
        statistics.startTime = startTime;

        //state = 1 = start
        changeState(startTime, startTime);

        HandelKey();
        byte[] ans = null;

        if(fileOrDir==1) {
            //file
            loggerData.addToLog("START Encryption for file: " + path);
            byteBuffer = ReadFileContent(path);
            ans = runTheAlgorithm(byteBuffer, algorithmNumber, key, true);
            GenerateNewFileAndPrintToIt(ans, "", path, 1);
            statistics.endTime = System.currentTimeMillis();
            if (statistics.status_succ){
                loggerData.addToLog("End Encryption of file: " + path + " - The time it took: " + statistics.processTimeToString());
            }
            statistics.file = new File(path);
            createXMLReports(statistics);
        }
        else{
            //directory

            //make the directory "/encrypted"
            File dir = new File(path + "/encrypted");
            dir.mkdir();

            if(syncOrAsync==1){
                //sync
                File[] listOfFiles = (new File(path)).listFiles();
                boolean choose = true;
                for (File file : listOfFiles) {
                    statistics.startTime = System.currentTimeMillis();
                    String fileName = file.getName();
                    if(!(fileName.equals("key.bin")) && !(fileName.equals("encrypted")) && !(file.isDirectory())) {
                        loggerData.addToLog("START Encryption for file: " + file.getName());
                        ans = runTheAlgorithm(ReadFileContent(file.getPath()), algorithmNumber, key, choose);
                        GenerateNewFileAndPrintToIt(ans, file.getName(), path, 2);
                        choose = false;
                        statistics.endTime = System.currentTimeMillis();
                        if (statistics.status_succ){
                            loggerData.addToLog("End Encryption of file: " + file.getName() + " - The time it took: " + statistics.processTimeToString());
                       }
                        statistics.file = file;
                        createXMLReports(statistics);
                    }
                }

            }
            else{
                //Async
                File[] listOfFiles = (new File(path)).listFiles();
                List<Thread> threads = new ArrayList<Thread>();
                boolean choose = true;
                for (File file : listOfFiles) {
                    statistics.startTime = System.currentTimeMillis();
                    String fileName = file.getName();
                    if(!(fileName.equals("key.bin")) && !(fileName.equals("encrypted")) && !(file.isDirectory())) {
                        loggerData.addToLog("START Encryption for file: " + file.getName());
                        Runnable threadJob = new FileThreadEncryption(key, path, algorithmNumber, file, choose, isWakeupNeeded);
                        Thread myThread = new Thread(threadJob);
                        myThread.setName(fileName);
                        threads.add(myThread);
                        myThread.start();
                        if(4 <= algorithmNumber && algorithmNumber<= 6) {
                            choose = false;
                        }
                        statistics.endTime = System.currentTimeMillis();
                        if(statistics.status_succ) {
                            loggerData.addToLog("End Encryption of file: " + file.getName() + " - The time it took: " + statistics.processTimeToString());
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
                        loggerData.addToLog("Failed Encryption of file: " + path + " because: " + e.getClass().toString());
                    } catch (Exception e){
                        e.printStackTrace();
                        statistics.status_succ = false;
                        statistics.exception = e;
                        statistics.file = new File (path);
                        loggerData.addToLog("Failed Encryption of file: " + path + " because: " + e.getClass().toString());
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
        removeObserver(observerEncryption);

    }
}


