

package io.renren.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date processing
 *
 * @author Mark sunlightcs@gmail.com
 */
public class DateUtils {
    /** Time format (yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** Time format (yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date formatting The date format is: yyyy-MM-dd
     * @param date date
     * @return Return yyyy-MM-dd format date
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * Date formatting The date format is: yyyy-MM-dd
     * @param date date
     * @param pattern format, such as: DateUtils.DATE_TIME_PATTERN
     * @return Return yyyy-MM-dd format date
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * Date analysis
     * @param date date
     * @param pattern format, such as: DateUtils.DATE_TIME_PATTERN
     * @return Return Date
     */
    public static Date parse(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
