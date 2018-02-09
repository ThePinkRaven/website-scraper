package com.tpr.ravennews.client;

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
        client.scrapeProfileData();
        client.scrapeActivitiesData();
        client.scrapeNewsData();
    }
}
