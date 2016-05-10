package com.answer.ninegridview.lib;

import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Created by jianhaohong on 5/9/16.
 */
public abstract class NineGridAdapter<T> {

    public abstract int getCount();

    public abstract int getItemLayoutRes(@LayoutRes int position);

    public abstract void onBindItemView(int position, View view);

    public abstract T getItem(int position);
}
