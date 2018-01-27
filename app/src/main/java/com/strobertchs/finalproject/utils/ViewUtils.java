package com.strobertchs.finalproject.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jenny on 2018-01-26.
 */

public class ViewUtils {
    /**
     * Follow URL encoding to encode email address by replacing "." with "%2e", and replacing "@" with "%40"
     * @param emailAddress String
     * @return String
     */
    public static String encodeEmailAddress(String emailAddress){
        return emailAddress.replaceAll("\\.", "%2e").replaceAll("@", "%40");
    }

    /**
     * Follow URL encoding to decode the encoded email address by replacing "%2e" with ".", and replacing "%40" with "@"
     * @param encodedEmailAddress String
     * @return String
     */
    public static String decodeEmailAddress(String encodedEmailAddress){
        return encodedEmailAddress.replaceAll("%2e", "\\.").replaceAll("%40", "@");
    }

    /**
     * Get the timestamp string
     * @param date Date
     * @return String
     */
    public static String getTimeStampString(Date date){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormatter.format(date);
    }

    /**
     * Get the timestamp string of the day beginning in format "yyyyMMdd"
     * @param date Date
     * @return String
     */
    public static String getTimeStampStringOfDayBeginning(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
        return dateFormatter.format(date);
    }
}
