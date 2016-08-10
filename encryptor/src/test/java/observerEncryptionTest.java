import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Elior on 2016-08-07.
 */
public @Data class observerEncryptionTest {
    @Test
    public void observerEncryption_start_Test1(){
        observerEncryption observerEncryption = new observerEncryption();
        observerEncryption.someoneChangedState(1);
        int ans = observerEncryption.currState;
        assertNotNull(ans);
        int wantedResult = 1;
        assertEquals(wantedResult, ans);
    }

    @Test
    public void observerEncryption_end_Test2(){
        observerEncryption observerEncryption = new observerEncryption();
        observerEncryption.someoneChangedState(0);
        int ans = observerEncryption.currState;
        assertNotNull(ans);
        int wantedResult = 0;
        assertEquals(wantedResult, ans);
    }

    @Test
    public void observerEncryptionTest3(){
        observerEncryption observerEncryption = new observerEncryption();
        observerEncryption.calculateAndPrintRunTime(10, 50);
        long ans = observerEncryption.runTime;
        assertNotNull(ans);
        long wantedResult = 40;
        assertEquals(wantedResult, ans);
    }

}