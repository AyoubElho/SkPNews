package com.example.skpnewsmvvm.AdapterSlider;

public class SliderItem {
    private String image;
    private String desc;
    private String time_news;
    private String title; // New field for date

    // Constructor with all fields
    public SliderItem() {

    }

    public SliderItem(String image, String desc, String time_news, String title) {
        this.image = image;
        this.desc = desc;
        this.time_news = time_news;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public String getDesc() {
        return desc;
    }

    public String getTime_news() {
        return time_news;
    }

    public String getTitle() {
        return title;
    }
}
