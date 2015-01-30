package com.richardpingree.navigationdrawer;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by richardpingree on 1/29/15.
 */
public class ImageGalleryFragment extends Fragment {

    public static final String TAG = "ImageGalleryFragment.TAG";

    private static final String ARG_SECTION_NUMBER = "section_number";

    ImageView image1, image2, image3, image4, image5, image6, image7,image8, image9;

    public static ImageGalleryFragment newInstance(int sectionNumber){
        ImageGalleryFragment fragment = new ImageGalleryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image_gallery, container, false);
        image1 = (ImageView) rootView.findViewById(R.id.imageView2);
        image2 = (ImageView) rootView.findViewById(R.id.imageView3);
        image3 = (ImageView) rootView.findViewById(R.id.imageView4);
        image4 = (ImageView) rootView.findViewById(R.id.imageView5);
        image5 = (ImageView) rootView.findViewById(R.id.imageView6);
        image6 = (ImageView) rootView.findViewById(R.id.imageView7);
        image7 = (ImageView) rootView.findViewById(R.id.imageView8);
        image8 = (ImageView) rootView.findViewById(R.id.imageView9);
        image9 = (ImageView) rootView.findViewById(R.id.imageView10);

        image1.setImageResource(R.drawable.photo2);
        image2.setImageResource(R.drawable.photo3);
        image3.setImageResource(R.drawable.photo4);
        image4.setImageResource(R.drawable.photo5);
        image5.setImageResource(R.drawable.photo6);
        image6.setImageResource(R.drawable.photo7);
        image7.setImageResource(R.drawable.photo8);
        image8.setImageResource(R.drawable.photo9);
        image9.setImageResource(R.drawable.photo10);

        return rootView;

    }

    public ImageGalleryFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

}
