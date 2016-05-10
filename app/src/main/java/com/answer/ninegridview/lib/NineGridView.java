package com.answer.ninegridview.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.answer.ninegridview.R;

/**
 * Created by jianhaohong on 5/9/16.
 */
public class NineGridView extends ViewGroup {

    private NineGridAdapter nineGridAdapter;
    private LayoutInflater layoutInflater;
    private int horizontalSpace;
    private int verticalSpace;
    private int columns;
    private int rows;

    public NineGridView(Context context) {
        super(context);
        init();
    }


    public NineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.NineGridView);
        verticalSpace = array.getDimensionPixelSize(R.styleable.NineGridView_verticalSpace, verticalSpace);
        horizontalSpace = array.getDimensionPixelOffset(R.styleable.NineGridView_horizontalSpace, horizontalSpace);
    }

    private void init() {
        layoutInflater = LayoutInflater.from(getContext());
        horizontalSpace = dpToPx(3);
        verticalSpace = dpToPx(3);
    }


    public NineGridAdapter getNineGridAdapter() {
        return nineGridAdapter;
    }

    public void setNineGridAdapter(NineGridAdapter nineGridAdapter) {
        this.nineGridAdapter = nineGridAdapter;
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        removeAllViews();

        if (nineGridAdapter == null || nineGridAdapter.getCount() == 0) {
            return;
        }

        calculateRowsAndColumns(nineGridAdapter.getCount());

        for (int i = 0; i < nineGridAdapter.getCount(); i++) {
            View itemView = layoutInflater.inflate(nineGridAdapter.getItemLayoutRes(i), this, false);
            nineGridAdapter.onBindItemView(i, itemView);
            addView(itemView);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() == 0) {
            return;
        }

        int childWidth = getChildAt(0).getMeasuredWidth();
        int childHeight = getChildAt(0).getMeasuredHeight();

        for (int i = 0; i < getChildCount(); i++) {

            int[] rowAndColumn = getChildRowAndColumn(i);
            int left = (childWidth + horizontalSpace) * rowAndColumn[1] + getPaddingLeft();
            int top = (childHeight + verticalSpace) * rowAndColumn[0] + getPaddingTop();
            int right = left + childWidth;
            int bottom = top + childHeight;
            getChildAt(i).layout(left, top, right, bottom);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (getChildCount() == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }


        if (getChildCount() == 1) {
            //if only one child, let child has its own size
            final View child = getChildAt(0);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(child.getMeasuredWidth(), child.getMeasuredHeight());
        } else {
            //if more than one child,than its max size equals (parentWidth - getPaddingLeft() - getPaddingRight() - gap * 2) / 3
            int parentWidth = MeasureSpec.getSize(widthMeasureSpec);

            int childItemSize = (parentWidth - getPaddingLeft() - getPaddingRight() - horizontalSpace * 2) / 3;

            int childMeasureSpec = MeasureSpec.makeMeasureSpec(childItemSize, MeasureSpec.EXACTLY);

            for (int i = 0; i < getChildCount(); i++) {
                getChildAt(i).measure(childMeasureSpec, childMeasureSpec);
            }

            int parentHeight = childItemSize * rows + verticalSpace * (rows - 1) + getPaddingTop() + getPaddingBottom();

            setMeasuredDimension(parentWidth, parentHeight);
        }
    }


    private int[] getChildRowAndColumn(int childIndex) {
        int[] rowAndColumn = new int[2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i * columns + j) == childIndex) {
                    rowAndColumn[0] = i;//行
                    rowAndColumn[1] = j;//列
                    break;
                }
            }
        }
        return rowAndColumn;
    }

    /**
     * 根据图片个数确定行列数量
     * 对应关系如下
     * num	row	column
     * 1	   1	1
     * 2	   1	2
     * 3	   1	3
     * 4	   2	2
     * 5	   2	3
     * 6	   2	3
     * 7	   3	3
     * 8	   3	3
     * 9	   3	3
     *
     * @param size
     */
    private void calculateRowsAndColumns(int size) {
        if (size <= 3) {
            rows = 1;
            columns = size;
        } else if (size <= 6) {
            rows = 2;
            columns = 3;
            if (size == 4) {
                columns = 2;
            }
        } else {
            rows = 3;
            columns = 3;
        }
    }

    public int dpToPx(float dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
        return (int) px;
    }

}
