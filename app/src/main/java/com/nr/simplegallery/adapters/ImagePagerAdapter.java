package com.nr.simplegallery.adapters;

import com.nr.simplegallery.ImageFragment;
import com.nr.simplegallery.model.ImageItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    private List<ImageItem> dataList;

    public ImagePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public ImagePagerAdapter setList(List<ImageItem> dataList) {
        this.dataList = dataList;
        return this;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(dataList.get(position).getJsonObject().toString());
    }

    @Override
    public int getCount() {
        return dataList.size();
    }
}
