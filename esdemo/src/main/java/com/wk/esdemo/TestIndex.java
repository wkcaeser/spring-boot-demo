package com.wk.esdemo;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "test", type = "person")
public class TestIndex {
    private String sex;
    private String name;
    private String description;
    private String title;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
