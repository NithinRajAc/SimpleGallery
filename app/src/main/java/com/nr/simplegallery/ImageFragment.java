package com.nr.simplegallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.nr.simplegallery.model.ImageItem;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

public class ImageFragment extends Fragment {
    private static final String JSON_DATA = "param1";
    private ImageItem imageItem;
    private PhotoView imageViewIV;
    private View detailsLL;
    private TextView detailsTV;
    private AppCompatImageView moreIV;

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

    private View.OnClickListener onClickDetailsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (detailsTV.getVisibility() == View.VISIBLE) {
                moreIV.setImageResource(R.drawable.ic_more_down_24);
                detailsTV.setVisibility(View.GONE);
            } else {

                moreIV.setImageResource(R.drawable.ic_more_up_24);
                detailsTV.setVisibility(View.VISIBLE);
            }
        }
    };

    private void loadView(View view) {

        imageViewIV = (PhotoView) view.findViewById(R.id.imageViewIV);
        moreIV = (AppCompatImageView) view.findViewById(R.id.moreIV);
        detailsTV = (TextView) view.findViewById(R.id.detailsTV);
        detailsLL = view.findViewById(R.id.detailsLL);
        detailsTV.setVisibility(View.GONE);

        if (imageItem == null) return;
        Glide.with(this)
                .load(imageItem.getHdurl())
                .thumbnail(Glide.with(this).load(imageItem.getUrl()))
                .centerCrop()
                .into(imageViewIV);
        ((TextView) view.findViewById(R.id.titleTV)).setText(imageItem.getTitle());
        String details = "explanation  :  " + imageItem.getExplanation() + "\n \ndate  :  " + imageItem.getDate();
        detailsTV.setText(details);
        detailsLL.setOnClickListener(onClickDetailsListener);
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
