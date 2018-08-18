package com.mysoft.university.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysoft.university.R;
import com.mysoft.university.mvp.model.entity.ClassInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页课程列表
 * <p>
 * Created by Zourw on 2018/8/12.
 */
public class IndexClassAdapter extends RecyclerView.Adapter<IndexClassAdapter.ViewHolder> {
    private Context context;
    private List<ClassInfo> infos;

    public IndexClassAdapter(Context context, List<ClassInfo> infos) {
        this.context = context;
        this.infos = infos;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon)
        ImageView mIcon;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.author)
        TextView mAuthor;
        @BindView(R.id.duration)
        TextView mDuration;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_index_class, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTitle.setText("标题:" + position);
    }

    @Override
    public int getItemCount() {
        return infos == null ? 0 : infos.size();
    }
}