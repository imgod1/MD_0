package com.example.md_0;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 康 on 2016/3/9.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Activity context;
    private List<String> name_list;


    public MyAdapter(Activity context, List<String> name_list) {
        this.context = context;
        this.name_list = name_list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //这里有一个坑点,就是如果用View.inflate(context, R.layout.item_recyclerview, null)的话,就算是在
        //item的布局文件中设置了高为多少,宽度为多少都是没用的,最后用LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
        // 而且这个方法的第二个参数最好设置为该parent
        //       View itemView = View.inflate(context, R.layout.item_recyclerview, null);
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.txt_name.setText(name_list.get(position));

        if (onItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLitener.onItemClick(holder.itemView, position);
                }
            });
        }

        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return name_list == null ? 0 : name_list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
        }
    }


    //ItemClickLitener
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener onItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }

    //ItemLongClickListener
    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

}
