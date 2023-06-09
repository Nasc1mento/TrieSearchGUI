package com.example.app.models;

import java.util.Objects;

public class Site {

    private String url;
    private String title;

    public Site(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public String getTitle() {
        return this.title;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if(obj == null)
            return false;
        if (!(obj instanceof Site)) {
            return false;
        }
        Site site = (Site) obj;
        return Objects.equals(url, site.url);
    }
}
