package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.model.type.PageType;

public class Configuration {
    private String url;
    private String name;
    private PageType pageType;

    public static Configuration of(String url, PageType type, String fileName) {
        return new Configuration()
                .setUrl(url)
                .setPageType(type)
                .setName(fileName);
    }

    public String getUrl() {
        return url;
    }

    public Configuration setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName() {
        return name;
    }

    public Configuration setName(String name) {
        this.name = name;
        return this;
    }

    public PageType getPageType() {
        return pageType;
    }

    public Configuration setPageType(PageType pageType) {
        this.pageType = pageType;
        return this;
    }
}
