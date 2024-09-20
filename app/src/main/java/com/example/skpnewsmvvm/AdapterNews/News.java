package com.example.skpnewsmvvm.AdapterNews;

public class News {
    private String image ,time_news;
    private String
            title, desc ,category;

    public News(){

    }

    public News(String image, String time_news, String title, String desc, String category) {
        this.image = image;
        this.time_news = time_news;
        this.title = title;
        this.desc = desc;
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getTime_news() {
        return time_news;
    }

    public String getCategory() {
        return category;
    }
}

