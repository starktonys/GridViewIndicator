package com.stark.gridviewindicator;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "debug";
    private ViewPager mViewPager;
    private CirclePageIndicator mIndicatorPointContainer;
    private AvatarPager mPager;
    private ArrayList<View> mList = new ArrayList<>();
    private ArrayList<PublicPage> mData = new ArrayList<>();

    //一页大小
    private int mPageSize = 8;
    private int mPageCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initViews();
    }

    private void initData() {
        for (int i = 0; i < 40; i++) {
            PublicPage publicPage = new PublicPage("http://7xq98z.com2.z0.glb.qiniucdn.com/20160514141652155194/icon_019_cover.png", "name" + (i + 1));
            mData.add(publicPage);
        }

        //计算有多少页数据
        mPageCount = (mData.size() + mPageSize - 1) / mPageSize;

        for (int i = 0; i < mPageCount; i++) {
            View pager = LayoutInflater.from(this).inflate(R.layout.grid_view_pager, null);
            GridView mGridView = (GridView) pager.findViewById(R.id.gridView);
            MyPagerAdapter pagerAdapter = new MyPagerAdapter(MainActivity.this, mData, i);
            pagerAdapter.setOnItemClickListener(new MyPagerAdapter.onItemClickListener() {
                @Override
                public void onItemClickListener(int position) {
                    // TODO: 2016/5/14  to do what you want
                    Toast.makeText(MainActivity.this, "position = " + position, Toast.LENGTH_SHORT).show();
                }
            });
            mGridView.setAdapter(pagerAdapter);
            mList.add(pager);
        }

    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mIndicatorPointContainer = (CirclePageIndicator) findViewById(R.id.indicator_point_container);

        mPager = new AvatarPager(mList);
        mViewPager.setAdapter(mPager);
        mIndicatorPointContainer.setViewPager(mViewPager);

    }

}
