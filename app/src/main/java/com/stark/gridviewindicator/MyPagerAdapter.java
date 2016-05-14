package com.stark.gridviewindicator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * 作者：Tony          <br/>
 * 描述：一页的样式          <br/>
 * 生成日期：2016/5/14    <br/>
 */
public class MyPagerAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<PublicPage> mShowData;
    private ArrayList<PublicPage> mTotalData;
    //一页显示8个
    private int pageItemCount = 8;
    private int totalSize;
    private int index;

    public MyPagerAdapter(Context context, ArrayList<PublicPage> data, int index) {
        mContext = context;
        mShowData = new ArrayList<>();
        this.index = index;
        totalSize = data.size();
        mTotalData = data;

        int list_index = index * pageItemCount;
        for (int i = list_index; i < data.size(); i++) {
            mShowData.add(data.get(i));
        }

    }

    @Override
    public int getCount() {
        int size = totalSize / pageItemCount; //计算有多少页
        if (index == size)
            return totalSize - pageItemCount * index;
        else
            return pageItemCount;
    }

    @Override
    public Object getItem(int position) {
        return mShowData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, null);
            holder.iv_icon = (CircleImageView) convertView.findViewById(R.id.civ_user_avatar);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_gv_item_Name);
            holder.iv_auth_status = (ImageView) convertView.findViewById(R.id.iv_auth_status);
            holder.civ_user_avatar_checked = (CircleImageView) convertView.findViewById(R.id.civ_user_avatar_checked);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bind(position, convertView, parent);
        return convertView;
    }

    class ViewHolder {
        CircleImageView iv_icon;
        TextView tv_name;
        ImageView iv_auth_status;
        CircleImageView civ_user_avatar_checked;

        public void bind(final int position, View convertView, ViewGroup parent) {
            Glide.with(mContext).load(mShowData.get(position).iconUrl).into(iv_icon);
            tv_name.setText(mShowData.get(position).name);

            iv_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positionInTotal = (index * pageItemCount + position);
                    mOnItemClickListener.onItemClickListener(positionInTotal);

                    if (!mShowData.get(position).isChecked) {
                        civ_user_avatar_checked.setVisibility(View.VISIBLE);
                        mShowData.get(position).isChecked = true;
                    } else {
                        civ_user_avatar_checked.setVisibility(View.GONE);
                        mShowData.get(position).isChecked = false;
                    }
                }
            });

        }
    }

    private onItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(onItemClickListener l) {
        this.mOnItemClickListener = l;
    }
    public interface onItemClickListener {
        void onItemClickListener(int position);
    }

}
