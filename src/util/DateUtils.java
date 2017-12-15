package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public final static String yyyy年MM月dd日_pattern = "yyyy年MM月dd日";
    public final static String yyyy年MM月dd日_HHmmss_pattern = "yyyy年MM月dd日 HH:mm:ss";
    public static String now(String pattern){
        return new SimpleDateFormat(pattern).format(new Date());
    }
}
