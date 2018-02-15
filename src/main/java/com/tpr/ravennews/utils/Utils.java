package com.tpr.ravennews.utils;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class Utils
{
    private Utils()
    {
    }

    public static String getCurrentTime()
    {
        return LocalTime.now(ZoneId.of(Configuration.TIME_ZONE)).format(DateTimeFormatter.ofPattern(Configuration.TIME_PATTERN));
    }

    public static String formatDots(String string)
    {
        if (string.endsWith("..."))
        {
            return string.replace("...", "");
        }

        return string;
    }

    public static void printlnWithColor(String color, String text)
    {
        System.out.println(color + text);
        System.out.println(ConsoleColors.RESET);
    }
}
