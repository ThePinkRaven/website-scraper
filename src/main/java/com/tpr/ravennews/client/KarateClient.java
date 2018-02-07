package com.tpr.ravennews.client;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.stream.Collectors;

public class KarateClient extends BaseClient
{
    public KarateClient(String profileUrl, String activitiesUrl, String newsUrl)
    {
        super(profileUrl, activitiesUrl, newsUrl);
    }

    @Override
    public void scrapeProfileData()
    {
        Elements div = profileData.getDocument().select("div").attr("class", "item-page");

        stringBuilder.append(div.select("h2").text());

        for (Element paragraph : div.select("p"))
        {
            stringBuilder.append(paragraph.text());
        }

        profileData.addData(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());
    }

    @Override
    public void scrapeActivitiesData()
    {

    }

    @Override
    public void scrapeNewsData()
    {
        Elements divs = newsData.getDocument().getElementsByTag("div").
                stream()
                .filter(div -> div.className().contains("leading-"))
                .collect(Collectors.toCollection(Elements::new));

        for (Element div : divs)
        {
            stringBuilder.append(div.select("h2").text());

            for (Element paragraph : div.select("p"))
            {
                if (!paragraph.getElementsByTag("a").isEmpty())
                {
                    for (Element link : paragraph.getElementsByTag("a"))
                    {
                        stringBuilder.append(paragraph.text())
                                .append(" link - > ")
                                .append(link.attr("abs:href"));
                    }
                } else
                {
                    stringBuilder.append(paragraph.text());
                }
            }

            newsData.addData(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        }
    }
}
