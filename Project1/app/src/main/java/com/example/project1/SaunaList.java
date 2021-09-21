package com.example.project1;

import java.util.LinkedList;

public class SaunaList extends LinkedList<Sauna> {
    private static SaunaList instance = null; // list does not exist initially.

    private SaunaList()
    {
        // Exists only to defeat additional instantiations.
    }

    public static SaunaList getInstance()
    {
        if(instance == null)
            instance = new SaunaList(); // create list here.

        return instance;
    }
}
