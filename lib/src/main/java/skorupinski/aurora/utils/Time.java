package skorupinski.aurora.utils;

import java.util.Date;

public class Time {
    
    public static long getMillis() {
        Date date = new Date();
        return date.getTime();
    }
}
