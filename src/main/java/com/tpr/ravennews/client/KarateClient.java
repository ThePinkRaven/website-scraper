package com.tpr.ravennews.client;

import com.tpr.ravennews.model.News;
import com.tpr.ravennews.utils.Utils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.stream.Collectors;

public class KarateClient extends BaseClient
{
    public KarateClient(String newsUrl)
    {
        super(newsUrl);
    }

    @Override
    public void scrapeNewsData()
    {
        clearTempNewsList();

        Elements divs = getHtmlDocument().getElementsByTag("div")
                .stream()
                .filter(div -> div.className().contains("leading-"))
                .collect(Collectors.toCollection(Elements::new));

        for (Element div : divs)
        {
            clearStringBuilderData();
            News news = new News();
            String title = Utils.formatDots(div.select("h2").text());

            if (!title.isEmpty())
            {
                news.setTitle(title);
            }

            for (Element paragraph : div.select("p"))
            {
                String paragraphText = Utils.formatDots(paragraph.text());

                if (!paragraph.getElementsByTag("a").isEmpty())
                {
                    for (Element link : paragraph.getElementsByTag("a"))
                    {
                        String linkString = paragraphText + "," + link.attr("abs:href");

                        news.addLink(linkString);
                    }
                } else
                {
                    if (!paragraphText.isEmpty())
                    {
                        stringBuilder
                                .append(paragraphText)
                                .append("\n");
                    }
                }
            }

            news.setContent(stringBuilder.toString());
            addNewsToTempList(news);
        }
    }

    private void clearStringBuilderData()
    {
        stringBuilder.delete(0, stringBuilder.length());
    }
}
