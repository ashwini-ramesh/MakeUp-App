package com.assignment.makeup_app.helper;

public class Utils {

    public static boolean isContentVerified(String value) {
        boolean result = false;
        if (value != null)
            if (!value.isEmpty())
                if (!value.equals("null"))
                    result = true;
        return result;

    }
}
