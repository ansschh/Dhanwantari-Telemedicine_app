package com.kanad.health;

public class Schemes {
    String Name;
    String About;
    String Link;
    String ImageUrl;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Schemes(String name, String about, String link, String imageUrl) {
        Name = name;
        About = about;
        Link = link;
        ImageUrl = imageUrl;
    }

    public Schemes() {
    }
}
