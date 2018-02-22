package com.tpr.ravennews.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class News
{
    private String title;
    private String content;
    private List<String> links;

    public News()
    {
        links = new ArrayList<>();
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public List<String> getLinks()
    {
        if (links.isEmpty())
        {
            return Collections.emptyList();
        }

        return links;
    }

    public void addLink(String link)
    {
        if (!link.isEmpty())
        {
            this.links.add(link);
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (title != null ? !title.equals(news.title) : news.title != null) return false;

        if (content != null ? !content.equals(news.content) : news.content != null) return false;

        return links != null ? links.equals(news.links) : news.links == null;
    }
}
