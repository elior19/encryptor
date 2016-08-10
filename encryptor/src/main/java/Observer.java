
/**
 * Created by Elior on 2016-08-02.
 */

// An interface to be implemented by everyone interested in change state events
public interface Observer {
    public void someoneChangedState(int state);
    public void calculateAndPrintRunTime(long startTime, long endTime);
}
