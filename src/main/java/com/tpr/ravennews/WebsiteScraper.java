package com.tpr.ravennews;

import com.tpr.ravennews.client.BaseClient;
import com.tpr.ravennews.client.KarateClient;
import com.tpr.ravennews.client.Scrapeable;

import java.util.ArrayList;
import java.util.List;

public final class WebsiteScraper
{
    private boolean running;
    private List<BaseClient> clientList;

    private WebsiteScraper()
    {
        this.running = false;
        this.clientList = new ArrayList<>();
    }

    public static void main(String[] args)
    {
        new WebsiteScraper().start();
    }

    private void start()
    {
        running = true;

        /*while (running)
        {
            refresh();
        }*/

        clientList.add(new KarateClient("http://karate.zgora.pl/index.php?option=com_content&view=article&id=6&Itemid=6", "http://karate.zgora.pl/index.php?option=com_content&view=category&id=14&Itemid=4", "http://karate.zgora.pl/"));
        clientList.get(0).scrapeProfileData();
        clientList.get(0).scrapeNewsData();

        for (String s : clientList.get(0).getProfileParsedData())
        {
            System.out.println(s);
        }
        for (String s : clientList.get(0).getNewsParsedData())
        {
            System.out.println(s);
        }
    }

    private void refresh()
    {
        for (Scrapeable client : clientList)
        {
            client.scrapeProfileData();
            client.scrapeActivitiesData();
            client.scrapeNewsData();
        }
    }

    public void stop()
    {
        running = false;
    }
}
