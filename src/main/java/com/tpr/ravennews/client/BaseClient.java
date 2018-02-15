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
    protected StringBuilder stringBuilder;
    private String newsUrl;
    private Document htmlDocument;
    private BigDecimal version;
    private List<News> currentNewsList;
    private List<News> tempNewsList;

    protected BaseClient(String newsUrl)
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

    protected Document getHtmlDocument()
    {
        return htmlDocument;
    }

    protected void addNewsToTempList(News news)
    {
        if (news != null)
        {
            this.tempNewsList.add(news);
        }
    }

    private void initNewsList()
    {
        if (version.equals(BigDecimal.ZERO) && !tempNewsList.isEmpty())
        {
            currentNewsList = tempNewsList;
            version = BigDecimal.ONE;

            Utils.printlnWithColor(APP_MSG_COLOR, APP_NEWS_INIT + version.toEngineeringString());
        }
    }

    private void updateNewsList()
    {
        if (!currentNewsList.equals(tempNewsList))
        {
            currentNewsList = tempNewsList;
            version = version.add(BigDecimal.valueOf(0.01));

            Utils.printlnWithColor(APP_MSG_COLOR, APP_NEWS_UPDATE + version.toEngineeringString());
        }
    }

    public void processNews()
    {
        initNewsList();
        updateNewsList();
    }

    public void printTempNewsList()
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
}
