package com.example.hiennv.loopingviewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingPagerAdapter;

import java.util.List;

public class LoopingAdapter extends LoopingPagerAdapter<Integer> {
    private static final int VIEW_TYPE_NORMAL = 100;
    private static final int VIEW_TYPE_SPECIAL = 101;

    public LoopingAdapter(Context context, List<Integer> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected int getItemViewType(int listPosition) {
        if (itemList.get(listPosition) == 0)
            return VIEW_TYPE_SPECIAL;
        return VIEW_TYPE_NORMAL;
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        if (viewType == VIEW_TYPE_NORMAL)
            return LayoutInflater.from(context).inflate(R.layout.item_pager, container, false);
        return LayoutInflater.from(context).inflate(R.layout.item_special, container, false);
    }

    @Override
    protected void bindView(View convertView, int listPosition, int viewType) {
        if (viewType == VIEW_TYPE_SPECIAL) return;
        View image = convertView.findViewById(R.id.image);
        TextView tvDescription = convertView.findViewById(R.id.tv_description);
        image.setBackgroundColor(context.getResources().getColor(getBackgroundColor(listPosition)));
        tvDescription.setText(listPosition + "");
    }

    private int getBackgroundColor(int number) {
        switch (number) {
            case 0:
                return android.R.color.holo_red_light;
            case 1:
                return android.R.color.holo_orange_light;
            case 2:
                return android.R.color.holo_green_light;
            case 3:
                return android.R.color.holo_blue_light;
            case 4:
                return android.R.color.holo_purple;
            case 5:
                return android.R.color.black;
            default:
                return android.R.color.black;
        }
    }
}
