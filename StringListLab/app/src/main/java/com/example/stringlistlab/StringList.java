package com.example.stringlistlab;

import java.util.LinkedList;

//////////////////////////////////////////////////////////////////////////
//
//  Singleton class StringList, for creating/managing a single
//  instance of a list of strings.  Note that the type "String"
//  can easily be changed to accommodate a list of whatever
//  kind of objects you wish.  (In such a case, it is a good idea
//  to change the class's name to reflect the objects it holds.)
//
//  Author: M. Halper
//
//////////////////////////////////////////////////////////////////////////

public final class StringList extends LinkedList<String>
{

    private static StringList instance = null; // list does not exist initially.

    private StringList()
    {
        // Exists only to defeat additional instantiations.
    }

    public static StringList getInstance()
    {
        if(instance == null)
            instance = new StringList(); // create list here.

        return instance;
    }

} // end StringList
