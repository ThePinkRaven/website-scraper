package com.tpr.ravennews.web;

import com.tpr.ravennews.utils.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Scraper
{
    public static Document getJsoupDocumentFromUrl(String url)
    {
        try
        {
            return Jsoup.connect(url)
                    .timeout(Configuration.CONNECTION_TIME_OUT)
                    .get();
        } catch (IOException e)
        {
            // todo proper error handling
            e.printStackTrace();
        }

        return null;
    }
}
