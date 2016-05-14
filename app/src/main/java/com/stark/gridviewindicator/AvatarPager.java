package com.stark.gridviewindicator;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 作者：Tony          <br/>
 * 描述：头像pager          <br/>
 * 生成日期：2016/5/14    <br/>
 */
public class AvatarPager extends PagerAdapter {

    private ArrayList<View> mList;

    public AvatarPager(ArrayList<View> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View pager = mList.get(position);
        container.addView(pager);
        return pager;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }
}
