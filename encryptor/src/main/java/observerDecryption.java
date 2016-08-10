
import lombok.Data;

/**
 * Created by Elior on 2016-08-02.
 */
// Someone interested in "Hello" events
public @Data class observerDecryption implements Observer {

    public int currState;
    public long runTime;

    public void someoneChangedState(int state) {
        if(state==1){ //  1 = start
            currState = 1;
            System.out.println("Event: Decryption started!");
        }
        else{ //  0 = end
            currState = 0;
            System.out.println("Event: Decryption ended!");
        }
    }

    public void calculateAndPrintRunTime(long startTime, long endTime){
        runTime = endTime - startTime;
        System.out.println("The whole process took "+ runTime + " Millis");
    }
}
