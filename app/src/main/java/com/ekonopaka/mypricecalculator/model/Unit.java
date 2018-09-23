package com.ekonopaka.mypricecalculator.model;

public enum Unit {
    m("м"), pc("шт");

    private final String title;

    Unit(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
