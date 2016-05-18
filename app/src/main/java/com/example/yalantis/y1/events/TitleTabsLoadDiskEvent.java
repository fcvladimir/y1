package com.example.yalantis.y1.events;

import java.util.List;

public class TitleTabsLoadDiskEvent {
    private List<String> listTitleTabs;

    public TitleTabsLoadDiskEvent(List<String> listTitleTabs) {
        this.listTitleTabs = listTitleTabs;
    }

    public List<String> getListTitleTabs() {
        return listTitleTabs;
    }

    public void setListTitleTabs(List<String> listTitleTabs) {
        this.listTitleTabs = listTitleTabs;
    }
}
