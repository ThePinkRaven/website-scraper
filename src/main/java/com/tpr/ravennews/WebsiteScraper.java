package com.tpr.ravennews;

import com.tpr.ravennews.client.BaseClient;
import com.tpr.ravennews.client.ClientWorker;
import com.tpr.ravennews.client.KarateClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public final class WebsiteScraper
{
    // test
    public static List<String> profileData = new ArrayList<>();
    private List<BaseClient> clientList;
    public static List<String> newsData = new ArrayList<>();
    private ScheduledExecutorService scheduledExecutorService;

    private WebsiteScraper()
    {
        this.clientList = new ArrayList<>();

        initClients();
        start();
    }

    public static void main(String[] args)
    {
        new WebsiteScraper();
    }

    public static synchronized void addData(String data)
    {
        //System.out.println(data);
        newsData.add(data);
        //System.out.println(newsData.size());
    }

    private void initClients()
    {
        // Dodanie klienta Karate

        clientList.add(new KarateClient("http://karate.zgora.pl/index.php?option=com_content&view=article&id=6&Itemid=6",
                "http://karate.zgora.pl/index.php?option=com_content&view=category&id=14&Itemid=4",
                "http://karate.zgora.pl/"));
    }

    private void start()
    {
        scheduledExecutorService = new ScheduledThreadPoolExecutor(4);
        refresh();
        scheduledExecutorService.shutdown();
    }

    private void refresh()
    {
        for (BaseClient client : clientList)
        {
            scheduledExecutorService.submit(new ClientWorker(client));
            //scheduledExecutorService.scheduleAtFixedRate(new ClientWorker(client), 1, 1, TimeUnit.SECONDS);
        }

        System.out.println("content");

        for (String s : profileData)
        {
            System.out.println(s);
        }
        for (String s : newsData)
        {
            System.out.println(s);
        }

        System.out.println("end content");
    }
}
