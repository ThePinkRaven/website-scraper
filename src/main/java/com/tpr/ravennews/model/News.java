package com.tpr.ravennews.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class News
{
    private String title;
    private String content;
    private List<String> links;

    public News()
    {
        this.links = new ArrayList<>();
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

        return Objects.equals(title, news.title) &&
                Objects.equals(content, news.content) &&
                Objects.equals(links, news.links);
    }
}
