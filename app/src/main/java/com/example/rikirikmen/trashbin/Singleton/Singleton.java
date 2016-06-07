package com.example.rikirikmen.trashbin.Singleton;

import java.util.List;
import java.util.Map;

import parceable.Trash;

/**
 * Created by Thomas on 6/6/2016.
 */

public class Singleton {
    private static Singleton singleton = new Singleton();

    // VARIABLES
    private Map<String, List<Trash>> itemCollections;
    // END of VARIABLES

    private Singleton() {
    }

    public static Singleton getInstance() {
        return singleton;
    }

    public Map<String, List<Trash>> getItemCollections() {
        return itemCollections;
    }

    public void setItemCollections(Map<String, List<Trash>> itemCollections) {
        this.itemCollections = itemCollections;
    }
}