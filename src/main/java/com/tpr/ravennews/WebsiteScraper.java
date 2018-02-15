package com.tpr.ravennews;

import com.tpr.ravennews.client.BaseClient;
import com.tpr.ravennews.client.ClientWorker;
import com.tpr.ravennews.client.KarateClient;
import com.tpr.ravennews.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.tpr.ravennews.utils.Configuration.*;

public final class WebsiteScraper
{
    private ScheduledExecutorService scheduledExecutorService;
    private List<BaseClient> clientList;

    private WebsiteScraper()
    {
        initClients();

        start();
    }

    public static void main(String[] args)
    {
        new WebsiteScraper();
    }

    private void initClients()
    {
        this.clientList = new ArrayList<>();

        // Add new clients here
        clientList.add(new KarateClient("http://karate.zgora.pl/"));
    }

    private void start()
    {
        Utils.printlnWithColor(SYSTEM_MSG_COLOR, APP_START);
        scheduledExecutorService = new ScheduledThreadPoolExecutor(POOL_SIZE);

        refresh();
    }

    private void refresh()
    {
        Utils.printlnWithColor(SYSTEM_MSG_COLOR, APP_REFRESH);

        for (BaseClient client : clientList)
        {
            scheduledExecutorService.scheduleWithFixedDelay(new ClientWorker(client),
                    INIT_DELAY,
                    REFRESH_DELAY,
                    TimeUnit.SECONDS);
        }
    }

    private void stop()
    {
        Utils.printlnWithColor(SYSTEM_MSG_COLOR, APP_STOP);
        scheduledExecutorService.shutdown();
    }
}
