package com.richardpingree.multipleactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Richard Pingree on 2/17/15.
 */
public class HeroAdapter extends BaseAdapter {

    private static final long ID_CONSTANT = 0x000000;

    Context mContext;
    ArrayList<Hero> mHeroes;

    public HeroAdapter(Context context, ArrayList<Hero> heroes) {
        mContext = context;
        mHeroes = heroes;
    }

    @Override
    public int getCount() {
        return mHeroes.size();
    }

    @Override
    public Hero getItem(int position) {
        return mHeroes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent,false);
        }

        Hero hero = getItem(position);
        TextView heroView = (TextView) convertView.findViewById(R.id.heroAlias);
        heroView.setText(hero.getAlias());
        return convertView;
    }
}
