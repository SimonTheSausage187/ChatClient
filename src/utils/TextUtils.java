package utils;

import java.util.Date;

public class TextUtils {


    public static String addTimeStamp(String s) {

        Date date = new Date();
        String dateStr = date.toString();

        String[] dateTokens = dateStr.split(" ");

        String time = "\t(" + dateTokens[3].substring(0, dateTokens[3].length() - 3) + ")";

        return (s + time);
    }
}



