package com.nr.simplegallery.view;

import android.os.Bundle;

import com.nr.simplegallery.R;
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
    }

    private void setBind() {
        viewModel = ViewModelProviders.of(this).get(ImageViewerViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_viewer);
        binding.setViewModel(viewModel);
    }

}
