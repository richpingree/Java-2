package com.richardpingree.multipleactivity;

import java.io.Serializable;

/**
 * Created by Richard Pingree on 2/17/15.
 */
public class Hero implements Serializable {

    public String mFirst;
    public String mLast;
    public String mAlias;
    public String mPower;

    public Hero(){
        mFirst = "";
        mLast = "";
        mAlias = "";
        mPower = "";
    }

    public Hero(String first, String last, String alias, String power){
        mFirst = first;
        mLast = last;
        mAlias = alias;
        mPower = power;
    }

    public String getFirst(){
        return mFirst;
    }

    public void setFirst(){
        this.mFirst = mFirst;
    }

    public String getLast(){
        return mLast;
    }

    public void setLast(){
        this.mLast = mLast;
    }

    public String getAlias(){
        return mAlias;
    }

    public void setAlias(){
        this.mAlias = mAlias;
    }

    public String getPower(){
        return mPower;
    }

    public void setPower(){
        this.mPower = mPower;
    }

    @Override
    public String toString() {
        return mAlias;
    }

}
