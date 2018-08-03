package com.zony.imageload.touchimage;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * 显示图片FAdapter
 *
 * @author zony
 * @time 18-7-5
 */
public class ShowPicAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> showItems;

    public ShowPicAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        if (showItems == null) {
            return 0;
        }
        return showItems.size();
    }

    public void setShowItems(ArrayList<String> showItems) {
        this.showItems = showItems;
        notifyDataSetChanged();
    }

    @Override
    public ShowPicFragment getItem(int position) {
        if (showItems == null || showItems.size() == 0) {
            return null;
        }

        return ShowPicFragment.create(showItems.get(position));
    }

    public void deleteCurrentItem(int currentItem) {
        showItems.remove(currentItem);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}