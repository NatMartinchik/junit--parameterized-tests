package com.martinchikn.domain;

public enum MenuItems {
     FIX("fixtures"), RES("results"), TRF("transfers");

    public final String tabName;

    MenuItems(String tabName) {
        this.tabName = tabName;
    }
}
