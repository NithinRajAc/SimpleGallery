package com.nr.simplegallery.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends AndroidViewModel {

    public MutableLiveData<String> text = new MutableLiveData<>("111");

    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
