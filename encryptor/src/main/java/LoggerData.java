
import lombok.Data;
import org.apache.log4j.Logger;

/**
 * Created by Elior on 2016-08-08.
 */
public @Data class LoggerData {
    final static Logger logger = Logger.getLogger(String.valueOf(LoggerData.class));

    public static void addToLog(String stringToLog){
        logger.info(stringToLog);
    }

}
