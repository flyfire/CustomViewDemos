package org.solarex.customviewdemos.widgets;

import java.util.List;

/**
 * Author: Solarex
 * Date: 2019/10/9
 * Desc:
 */
public class DynamicMultiChoiceEntity {
    private String title;
    private List<String> contents;

    public DynamicMultiChoiceEntity(String title, List<String> contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getContents() {
        return contents;
    }
}
