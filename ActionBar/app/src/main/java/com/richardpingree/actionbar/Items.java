package com.richardpingree.actionbar;

import java.io.Serializable;

/**
 * Created by Richard Pingree on 1/27/15.
 */
public class Items implements Serializable {

    private String mName;

    public Items(String name){

        mName = name;

    }

    public String getName(){
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    @Override
    public String toString() {
        return mName;
    }
}
