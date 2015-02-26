package com.richardpingree.navigationdrawer;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Richard Pingree on 2/24/15.
 */
public class NewsGallery  extends Fragment{

    public static final String TAG = "NewsGallery.TAG";

    private static final String ARG_SECTION_NUMBER = "section_number";

    ImageView image1, image2, image3, image4;

    public  NewsGallery(){

    }

   public static NewsGallery newInstance(int sectionNumber){
       NewsGallery frag = new NewsGallery();
       Bundle args = new Bundle();
       args.putInt(ARG_SECTION_NUMBER, sectionNumber);
       frag.setArguments(args);
       return frag;
   }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_gallery, container, false);
        image1 = (ImageView) rootView.findViewById(R.id.imageView2);
        image2 = (ImageView) rootView.findViewById(R.id.imageView3);
        image3 = (ImageView) rootView.findViewById(R.id.imageView4);
        image4 = (ImageView) rootView.findViewById(R.id.imageView5);

        image1.setImageResource(R.drawable.breakfast);
        image2.setImageResource(R.drawable.money);
        image3.setImageResource(R.drawable.pizza);
        image4.setImageResource(R.drawable.thor);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
