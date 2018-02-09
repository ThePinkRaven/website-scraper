package com.tpr.ravennews.client;

public abstract class BaseClient implements Scrapeable
{
    StringBuilder stringBuilder;

    ClientData profileData;
    ClientData activitiesData;
    ClientData newsData;

    BaseClient(String profileUrl, String activitiesUrl, String newsUrl)
    {
        initClient(profileUrl, activitiesUrl, newsUrl);
    }

    private void initClient(String profileUrl, String activitiesUrl, String newsUrl)
    {
        stringBuilder = new StringBuilder();

        profileData = new ClientData(profileUrl);
        activitiesData = new ClientData(activitiesUrl);
        newsData = new ClientData(newsUrl);
    }

    /*public List<String> getProfileParsedData()
    {
        return profileData.getData();
    }

    public List<String> getActivitiesParsedData()
    {
        return activitiesData.getData();
    }

    public List<String> getNewsParsedData()
    {
        return newsData.getData();
    }*/
}
