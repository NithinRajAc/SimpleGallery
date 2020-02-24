package com.nr.simplegallery.view;

import android.os.Bundle;

import com.nr.simplegallery.R;
import com.nr.simplegallery.adapters.ImageListAdapter;
import com.nr.simplegallery.databinding.ActivityMainBinding;
import com.nr.simplegallery.utilities.Constants;
import com.nr.simplegallery.viewModel.MainViewModel;

import org.json.JSONArray;
import org.json.JSONException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    private ImageListAdapter adapter;                       // adapter of for image item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBind();                                          // set binding into view and view model
        setAdapter();                                       // set adapter
    }

    private void setBind() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel);
    }

    private void setAdapter() {

        binding.imagesRV.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        try {
            adapter = new ImageListAdapter(this).setList(new JSONArray(Constants.data));
            binding.imagesRV.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
