import lombok.Data;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Elior on 2016-08-10.
 */
public @Data class StatisticsTest {

    @Test
    public void Statistics_status_succ_ToString_true_Test1(){
        Statistics Statistics = new Statistics();
        Statistics.setStatus_succ(true);
        String ans = Statistics.status_succ_ToString();
        assertNotNull(ans);
        String wantedResult = "succeeded";
        assertEquals(wantedResult, ans);
    }

    @Test
    public void Statistics_status_succ_ToString_false_Test2(){
        Statistics Statistics = new Statistics();
        Statistics.setStatus_succ(false);
        String ans = Statistics.status_succ_ToString();
        assertNotNull(ans);
        String wantedResult = "failed";
        assertEquals(wantedResult, ans);
    }

    @Test
    public void Statistics_processTimeToString_Test3(){
        Statistics Statistics = new Statistics();
        Statistics.setStartTime(10);
        Statistics.setEndTime(25);
        String ans = Statistics.processTimeToString();
        assertNotNull(ans);
        String wantedResult = "15";
        assertEquals(wantedResult, ans);
    }
}