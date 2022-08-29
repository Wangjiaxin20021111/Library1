package demo13.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;


/**
 * @author 25043
 */
public class MyFormat extends Formatter {
    @Override
    public String format(LogRecord record) {
        ZonedDateTime zdf = ZonedDateTime.now();
        String sDate = zdf.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return "[time：" + sDate + "]——" + "[level：" + record.getLevel().getName() + "]——" + "[location：" + record.getSourceClassName() + " " + record.getSourceMethodName() + "]——" + "[message：" + record.getMessage() + "]\n";
    }
}

