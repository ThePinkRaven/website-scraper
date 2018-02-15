package com.tpr.ravennews.utils;

public class Configuration
{
    public static final int CONNECTION_TIME_OUT = 10000;

    public static final int POOL_SIZE = 1;

    public static final long INIT_DELAY = 1;
    public static final long REFRESH_DELAY = 10;

    public static final String SYSTEM_MSG_COLOR = ConsoleColors.RED_BOLD_BRIGHT;
    public static final String APP_MSG_COLOR = ConsoleColors.RED_BACKGROUND;

    public static final String APP_START = "Aplikacja została uruchomiona!";
    public static final String APP_STOP = "Aplikacja została zamknięta!";
    public static final String APP_REFRESH = "Uruchomiono odświeżanie danych!";
    public static final String APP_CHECK_CLIENT = "Sprawdzanie danych dla klienta: ";
    public static final String APP_NO_NEWS = "Nie ma żadnych newsów!";
    public static final String APP_NEWS_INIT = "Zainicializowano dane! Wersja -> ";
    public static final String APP_NEWS_UPDATE = "Newsy zostały zaaktualizowane! Wersja -> ";

    public static final String TIME_ZONE = "GMT+01:00";
    public static final String TIME_PATTERN = "HH:mm:ss";
}