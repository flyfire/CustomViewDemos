package org.solarex.customviewdemos.widgets;

import java.util.List;

/**
 * Author: Solarex
 * Date: 2019/10/8
 * Desc:
 */
public class DynamicSingleChoiceEntity {
    private String ivHeadUrl;
    private String title;
    private List<String> contents;

    public DynamicSingleChoiceEntity(String ivHeadUrl, String title, List<String> contents) {
        this.ivHeadUrl = ivHeadUrl;
        this.title = title;
        this.contents = contents;
    }

    public String getIvHeadUrl() {
        return ivHeadUrl;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getContents() {
        return contents;
    }
}
