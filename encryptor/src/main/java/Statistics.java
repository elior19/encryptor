
import lombok.Data;

import java.io.File;

/**
 * Created by Elior on 2016-08-07.
 */
public @Data class Statistics{
    public File file;
    public long startTime;
    public long endTime;
    public Exception exception;
    public Boolean status_succ = true; //true = succeed   false = fail

    public String status_succ_ToString(){
        if(status_succ){
            return "succeeded";
        }
        else{
            return "failed";
        }
    }
    public String processTimeToString(){
        long processTime = endTime - startTime;
        return String.valueOf(processTime);
    }

}
