package com.tpr.ravennews.utils;

import com.tpr.ravennews.model.News;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public static boolean areNewsCollectionsEqual(List<News> news1, List<News> news2)
    {
        if (news1.size() == news2.size())
        {
            for (int i = 0; i < news1.size(); i++)
            {
                if (!news1.get(i).equals(news2.get(i)))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }
}
