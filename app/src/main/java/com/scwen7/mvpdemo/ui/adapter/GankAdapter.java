package com.scwen7.mvpdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwen7.mvpdemo.R;
import com.scwen7.mvpdemo.data.bean.GankBean;

import java.util.List;

/**
 * Created by 解晓辉 on 2017/8/15.
 * 作用：
 */

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.MyViewHolder> {

    private Context mContext;
    private List<GankBean> mGankBeanList;
    private LayoutInflater mLayoutInflater;


    public GankAdapter(Context context, List<GankBean> gankBeanList) {
        this.mContext = context;
        this.mGankBeanList = gankBeanList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void addData(int position, List<GankBean> data) {
        if (data != null && data.size() > 0) {
            mGankBeanList.addAll(position, data);
            notifyItemRangeInserted(position, data.size());
        }
    }

    public void setData(List<GankBean> list) {
        if (list != null) {
            mGankBeanList = list;
            notifyDataSetChanged();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = mLayoutInflater.inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setData();
    }

    @Override
    public int getItemCount() {
        return mGankBeanList == null ? 0 : mGankBeanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvUrl;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvUrl = (TextView) itemView.findViewById(R.id.tv_url);
        }

        public void setData() {
            int position = getAdapterPosition();
            GankBean gankBean = mGankBeanList.get(position);
            tvTitle.setText(gankBean.getDesc());
            tvUrl.setText(gankBean.getUrl());
        }
    }
}
