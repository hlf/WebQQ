package com.ltype.webqq.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {
	public String findFirst(String p, String m) {
        String result = "";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(m);
        if (matcher.find())
            result = matcher.group();
        return result;
    }
}