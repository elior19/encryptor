import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Elior on 2016-08-07.
 */
public @Data class observerDecryptionTest {
    @Test
    public void observerDecryption_start_Test1(){
        observerDecryption observerDecryption = new observerDecryption();
        observerDecryption.someoneChangedState(1);
        int ans = observerDecryption.currState;
        assertNotNull(ans);
        int wantedResult = 1;
        assertEquals(wantedResult, ans);
    }

    @Test
    public void observerDecryption_end_Test2(){
        observerDecryption observerDecryption = new observerDecryption();
        observerDecryption.someoneChangedState(0);
        int ans = observerDecryption.currState;
        assertNotNull(ans);
        int wantedResult = 0;
        assertEquals(wantedResult, ans);
    }

    @Test
    public void observerDecryptionTest3(){
        observerDecryption observerDecryption = new observerDecryption();
        observerDecryption.calculateAndPrintRunTime(10, 50);
        long ans = observerDecryption.runTime;
        assertNotNull(ans);
        long wantedResult = 40;
        assertEquals(wantedResult, ans);
    }

}