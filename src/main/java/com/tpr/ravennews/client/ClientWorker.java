package com.tpr.ravennews.client;

import com.tpr.ravennews.utils.Utils;

import static com.tpr.ravennews.utils.Configuration.APP_CHECK_CLIENT;
import static com.tpr.ravennews.utils.Configuration.APP_MSG_COLOR;

public class ClientWorker implements Runnable
{
    private BaseClient client;

    public ClientWorker(BaseClient client)
    {
        this.client = client;
    }

    @Override
    public void run()
    {
        Utils.printlnWithColor(APP_MSG_COLOR, APP_CHECK_CLIENT + client.getClass().getSimpleName() + " -> " + Utils.getCurrentTime());

        client.scrapeNewsData();
        client.updateCurrentNewsList();
    }
}
