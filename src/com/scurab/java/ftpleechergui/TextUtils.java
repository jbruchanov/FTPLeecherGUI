package com.scurab.java.ftpleechergui;

/**
 * Created with IntelliJ IDEA.
 * User: Joe Scurab
 * Date: 12.2.13
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
public class TextUtils {

    public static boolean isNullOrEmpty(String v) {
        return v == null || v.length() == 0;
    }

    public static String nz(String v, String ifnull) {
        return isNullOrEmpty(v) ? ifnull : v;
    }
}
