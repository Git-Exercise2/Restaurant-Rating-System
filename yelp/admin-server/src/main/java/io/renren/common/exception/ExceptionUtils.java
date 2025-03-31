

package io.renren.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception tool class
 *
 * @author Mark sunlightcs@gmail.com
 */
public class ExceptionUtils {

    /**
     * Get exception information
     * @param ex Exception
     * @return Return exception information
     */
    public static String getErrorStackTrace(Exception ex){
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw, true);
            ex.printStackTrace(pw);
        }finally {
            try {
                if(pw != null) {
                    pw.close();
                }
            } catch (Exception e) {

            }
            try {
                if(sw != null) {
                    sw.close();
                }
            } catch (IOException e) {

            }
        }

        return sw.toString();
    }
}