package com.springboot.empc.utils;

import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {

    // private static final String regexEmail = "^(.+)@(.+)$";
    // public static final String regexEmail =
    // "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public static final String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static final String regexUUID = "^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$";

    public static final int paginationElementSize = 10;

    public static final String basePackage = "com.springboot.empc";
    public static final String fcmServer = "app-firebase.json";

    public static boolean checkNotNull(String s) {
        if (!Objects.nonNull(s)) {
            return false;
        }
        if (s == null) {
            return false;
        }
        s = s.trim();
        if (s.equals("") || s.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean validMobile(String m) {
        if (checkNotNull(m)) {
            return false;
        }
        String pattern1 = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        // String pattern2 = "^\\d{10}$";
        Pattern p = Pattern.compile(pattern1);
        Matcher matcher = p.matcher(m);
        return matcher.matches();
    }

    public static boolean checkNotZero(int i) {
        if (i == 0) {
            return false;
        }
        return true;
    }

    public final static String intArray = "integer-array";
    public final static String strArray = "string-array";

    public static Integer generateOTP() {
        Random random = new Random();
        int OTP = 100000 + random.nextInt(900000);
        return OTP;
    }
}
