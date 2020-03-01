package com.nr.simplegallery.view;

import android.os.Bundle;

import com.nr.simplegallery.R;
import com.nr.simplegallery.adapters.ImagePagerAdapter;
import com.nr.simplegallery.databinding.ActivityImageViewerBinding;
import com.nr.simplegallery.viewModel.ImageViewerViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class ImageViewerActivity extends AppCompatActivity {

    private ImageViewerViewModel viewModel;
    private ActivityImageViewerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBind();
        setData();
        setAdapter();                                          //setting viewpager adapter
    }

    private void setBind() {
        viewModel = ViewModelProviders.of(this).get(ImageViewerViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_viewer);
        binding.setViewModel(viewModel);
    }

    private void setData() {
        viewModel.selected_image_pos = getIntent().getIntExtra("image_position", 0);
    }

    private void setAdapter() {
        ImagePagerAdapter adapter = new ImagePagerAdapter(getSupportFragmentManager(), 0).setList(viewModel.dataList);
        binding.imageVP.setAdapter(adapter);
        binding.imageVP.setCurrentItem(viewModel.selected_image_pos);
    }
}
