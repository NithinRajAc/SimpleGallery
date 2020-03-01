package com.nr.simplegallery.viewModel;

import android.app.Application;

import com.nr.simplegallery.model.ImageItem;
import com.nr.simplegallery.utilities.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class ImageViewerViewModel extends AndroidViewModel {

    public int selected_image_pos = 0;
    public List<ImageItem> dataList = new ArrayList<>();


    public ImageViewerViewModel(@NonNull Application application) {
        super(application);
        setList();
    }

    public void setList() {           // adding images items into datalist
        JSONArray array = null;
        try {
            array = new JSONArray(Constants.data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.optJSONObject(i);
                ImageItem item = new ImageItem(object);
                dataList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
