package com.answer.ninegridview.app;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;

import com.answer.ninegridview.lib.NineGridAdapter;
import com.answer.ninegridview.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by jianhaohong on 5/9/16.
 */
public class DemoNineGridAdapter extends NineGridAdapter<String> {

    private List<String> imageList;
    private Context context;

    public DemoNineGridAdapter(Context context, List<String> imageList) {
        this.imageList = imageList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public int getItemLayoutRes(@LayoutRes int position) {
        if (imageList.size() == 1) {
            return R.layout.item_view_big_image;
        } else {
            return R.layout.item_view_normal;
        }

    }

    @Override
    public void onBindItemView(int position, View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        Glide.with(context).load(getItem(position)).placeholder(R.color.colorAccent).into(imageView);
    }

    @Override
    public String getItem(int position) {
        return imageList.get(position);
    }
}
