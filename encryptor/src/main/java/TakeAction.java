
/**
 * Created by Elior on 2016-08-01.
 */
public interface TakeAction {
    abstract public void Run();
    abstract public byte[] ReadFileContent(String path);
    abstract public void HandelKey();
    abstract public void GenerateNewFileAndPrintToIt(byte[] ans, String fileInDirectoryName, String path, int fileOrDir);
    public void addObserver(Observer toAdd);
    public void removeObserver(Observer toRemove);
    public void changeState(long startTime, long endTime);
    public byte[] runTheAlgorithm(byte[] byteBuffer, int AlgorithmNumber, Key key, boolean choose);
}
