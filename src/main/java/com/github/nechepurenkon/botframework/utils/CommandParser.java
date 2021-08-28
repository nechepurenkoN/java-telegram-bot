package com.github.nechepurenkon.botframework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Pair;

public class CommandParser {
    public static Pair<String, String> getCommand(String text) {
        Pattern p = Pattern.compile("/(\\w+)( (.*))*");
        Matcher m = p.matcher(text);
        return m.matches() ? new Pair<>(
            m.group(1),
            m.group(3) != null ? m.group(3) : ""
        ) : new Pair<>(
            "empty",
            text
        );
    }
}
