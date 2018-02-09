package com.tpr.ravennews.client;

import com.tpr.ravennews.WebsiteScraper;
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
        clearStringBuilderData();

        Elements div = profileData.getDocument().select("div")
                .attr("class", "item-page");

        stringBuilder.append(div.select("h2").text());

        for (Element paragraph : div.select("p"))
        {
            stringBuilder.append(paragraph.text());
        }

        WebsiteScraper.profileData.add("asdadasdas");

        // profileData.addData(stringBuilder.toString());
    }

    @Override
    public void scrapeActivitiesData()
    {
        clearStringBuilderData();
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
            clearStringBuilderData();

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

            WebsiteScraper.addData("dupa");

            // newsData.addData(stringBuilder.toString());
        }
    }

    private void clearStringBuilderData()
    {
        stringBuilder.delete(0, stringBuilder.length());
    }
}
