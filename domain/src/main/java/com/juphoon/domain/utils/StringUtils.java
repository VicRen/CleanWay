package com.juphoon.domain.utils;

public class StringUtils {

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.equals("");
    }

    public static boolean equals(CharSequence a, CharSequence b) {
        if(a == b) {
            return true;
        } else {
            int length;
            if(a != null && b != null && (length = a.length()) == b.length()) {
                if(a instanceof String && b instanceof String) {
                    return a.equals(b);
                } else {
                    for(int i = 0; i < length; ++i) {
                        if(a.charAt(i) != b.charAt(i)) {
                            return false;
                        }
                    }

                    return true;
                }
            } else {
                return false;
            }
        }
    }
}
