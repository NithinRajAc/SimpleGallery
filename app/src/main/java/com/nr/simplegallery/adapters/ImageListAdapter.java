package com.nr.simplegallery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nr.simplegallery.R;
import com.nr.simplegallery.interfaces.ImageListListener;
import com.nr.simplegallery.model.ImageItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {

    private List<ImageItem> dataList;
    private Context context;
    private ImageListListener listener;

    public ImageListAdapter(Context context) {
        this.context = context;
        this.dataList = new ArrayList<>();
    }

    public ImageListAdapter setListener(ImageListListener listener) {
        this.listener = listener;
        return this;
    }

    public ImageListAdapter setList(JSONArray array) {           // adding images items into datalist

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.optJSONObject(i);
            ImageItem item = new ImageItem(object);
            dataList.add(item);
        }
        notifyDataSetChanged();
        return this;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIV = (ImageView) itemView.findViewById(R.id.imageIV);
            itemView.setOnClickListener(this);
        }

        void setData(ImageItem item) {                              //seting data for view holder

            Glide.with(context)
                    .load(item.getUrl())
                    .thumbnail(Glide.with(context).load(item.getUrl()))
                    .centerCrop()
                    .into(imageIV);
        }

        @Override
        public void onClick(View view) {
            if (listener == null) return;
            listener.onClickImageItem(dataList.get(getAdapterPosition()), getAdapterPosition());
        }
    }
}
