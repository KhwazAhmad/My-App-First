package com.example.practiceapi2march.Model;

public class ModelMemes
{
    private String[] preview;

    private String postLink;

    private String nsfw;

    private String author;

    private String ups;

    private String spoiler;

    private String title;

    private String subreddit;

    private String url;

    public String[] getPreview ()
    {
        return preview;
    }

    public void setPreview (String[] preview)
    {
        this.preview = preview;
    }

    public String getPostLink ()
    {
        return postLink;
    }

    public void setPostLink (String postLink)
    {
        this.postLink = postLink;
    }

    public String getNsfw ()
    {
        return nsfw;
    }

    public void setNsfw (String nsfw)
    {
        this.nsfw = nsfw;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getUps ()
    {
        return ups;
    }

    public void setUps (String ups)
    {
        this.ups = ups;
    }

    public String getSpoiler ()
    {
        return spoiler;
    }

    public void setSpoiler (String spoiler)
    {
        this.spoiler = spoiler;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getSubreddit ()
    {
        return subreddit;
    }

    public void setSubreddit (String subreddit)
    {
        this.subreddit = subreddit;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [preview = "+preview+", postLink = "+postLink+", nsfw = "+nsfw+", author = "+author+", ups = "+ups+", spoiler = "+spoiler+", title = "+title+", subreddit = "+subreddit+", url = "+url+"]";
    }
}


