package com.yqy.baseframe.frame;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yqy.baseframe.listener.OnRecyclerViewListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本Recycler适配器
 * Created by yanqy on 2017/3/29.
 */

public abstract class BaseRecyclerViewAdapter<D,VH extends BaseRecyclerViewAdapter.BaseViewHolder> extends RecyclerView.Adapter<VH>{

    private int layoutResId; //item资源Id
    private List<D> data; //数据集合
    private OnRecyclerViewListener listener;

    public BaseRecyclerViewAdapter(int layoutResId, List<D> data, OnRecyclerViewListener listener) {
        this.data = data == null? new ArrayList<D>():data;
        if(layoutResId != 0) this.layoutResId = layoutResId;
        else throw new NullPointerException("请设置Item资源Id");
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return (VH) new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutResId,parent,false));
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        bindData(holder,data.get(position));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) listener.onItemClick(position);
            }
        });
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(listener != null) listener.onItemLongClick(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * 绑定数据
     * @param  holder
     * @param data
     */
    protected abstract void bindData(VH holder,D data);

    public class BaseViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public BaseViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
