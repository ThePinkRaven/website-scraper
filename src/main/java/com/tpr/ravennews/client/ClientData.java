package com.tpr.ravennews.client;

import com.tpr.ravennews.utils.Scraper;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class ClientData
{
    private String url;
    private Document document;
    private List<String> data;

    ClientData(String url)
    {
        this.url = url;
        this.document = initDocument();
        this.data = new ArrayList<>();
    }

    private Document initDocument()
    {
        return Scraper.getJsoupDocumentFromUrl(this.url);
    }

    public Document getDocument()
    {
        return document;
    }

    public void addData(String data)
    {
        this.data.add(data);
    }

    public List<String> getData()
    {
        return this.data;
    }
}
