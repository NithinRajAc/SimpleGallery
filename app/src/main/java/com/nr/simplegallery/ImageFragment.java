package com.nr.simplegallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.nr.simplegallery.model.ImageItem;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ImageFragment extends Fragment {
    private static final String JSON_DATA = "param1";
    private ImageItem imageItem;
    private PhotoView imageViewIV;

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(String jsonData) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(JSON_DATA, jsonData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try {
                imageItem = new ImageItem(new JSONObject(getArguments().getString(JSON_DATA)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadView(view);
    }

    private void loadView(View view) {

        imageViewIV = (PhotoView) view.findViewById(R.id.imageViewIV);

        Glide.with(this)
                .load(imageItem.getHdurl())
                .thumbnail(Glide.with(this).load(imageItem.getUrl()))
                .centerCrop()
                .into(imageViewIV);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
