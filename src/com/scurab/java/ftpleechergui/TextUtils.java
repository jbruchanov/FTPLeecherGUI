package com.scurab.java.ftpleechergui;

import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Joe Scurab
 * Date: 12.2.13
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
public class TextUtils {

    private static DecimalFormat NUMBER_FORMAT = new DecimalFormat();

    static {
        NUMBER_FORMAT.setGroupingSize(3);
        NUMBER_FORMAT.setGroupingUsed(true);
    }

    public static boolean isNullOrEmpty(String v) {
        return v == null || v.length() == 0;
    }

    public static String nz(String v, String ifnull) {
        return isNullOrEmpty(v) ? ifnull : v;
    }

    public static String getNumberReadable(int value){
        return NUMBER_FORMAT.format(value);
    }

    public static  String getSpeedReadable(int value){
        if(value > 1000000){
            return String.format("%.2f MiB/s", (value / 1000000f));
        }
        else if(value > 1000){
            return String.format("%.0f KiB/s", (value / 1000f));
        }else{
            return value + " B/s";
        }
    }
}
