package com.tpr.ravennews.client;

import com.tpr.ravennews.model.News;
import com.tpr.ravennews.utils.ConsoleColors;
import com.tpr.ravennews.utils.Utils;
import com.tpr.ravennews.web.Scrapeable;
import com.tpr.ravennews.web.Scraper;
import org.jsoup.nodes.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.tpr.ravennews.utils.Configuration.*;

public abstract class BaseClient implements Scrapeable
{
    StringBuilder stringBuilder;
    private String newsUrl;
    private Document htmlDocument;
    private BigDecimal version;
    private List<News> currentNewsList;
    private List<News> tempNewsList;

    BaseClient(String newsUrl)
    {
        this.stringBuilder = new StringBuilder();
        this.newsUrl = newsUrl;
        this.version = new BigDecimal(0);

        this.htmlDocument = initDocument();
        this.currentNewsList = new ArrayList<>();
        this.tempNewsList = new ArrayList<>();
    }

    private Document initDocument()
    {
        return Scraper.getJsoupDocumentFromUrl(this.newsUrl);
    }

    Document getHtmlDocument()
    {
        return htmlDocument;
    }

    void addNewsToTempList(News news)
    {
        if (news != null)
        {
            this.tempNewsList.add(news);
        }
    }

    public void updateCurrentNewsList()
    {
        printTempNewsList();

        // todo sprawdzić dlaczego za każdym razem lista pobrana jest inna niż ta current : ( powinna być taka sama skoro nie zmienili newsów
        // edit: napisalem swoja metode sprawdzania kolekcji i niby dziala - do testow

        if (!Utils.areNewsCollectionsEqual(currentNewsList, tempNewsList) && !tempNewsList.isEmpty())
        {
            updateCurrentListContentsAndVersion(APP_NEWS_UPDATE);
        }
    }

    private void printTempNewsList()
    {
        if (tempNewsList.isEmpty())
        {
            Utils.printlnWithColor(ConsoleColors.PURPLE, APP_NO_NEWS);
        }

        for (News news : tempNewsList)
        {
            System.out.println(ConsoleColors.BLUE + news.getTitle());
            System.out.println(ConsoleColors.PURPLE + news.getContent());
        }
    }

    private void updateCurrentListContentsAndVersion(String message)
    {
        try
        {
            currentNewsList.clear();
            currentNewsList.addAll(tempNewsList);
            version = version.add(BigDecimal.valueOf(0.01));
            Utils.printlnWithColor(APP_MSG_COLOR, message + version.toEngineeringString());
        } catch (Exception e)
        {
            System.out.println("ERRRRRRRRRR -> " + e.getMessage() + " <- ERRRRRRRRRR");
        }
    }

    void clearTempNewsList()
    {
        tempNewsList.clear();
    }
}
