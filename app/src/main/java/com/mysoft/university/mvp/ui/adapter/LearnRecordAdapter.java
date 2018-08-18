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
import com.mysoft.university.mvp.model.entity.LearnRecordInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 学习记录
 * <p>
 * Created by Zourw on 2018/8/18.
 */
public class LearnRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<LearnRecordInfo> infos;

    public LearnRecordAdapter(Context context, List<LearnRecordInfo> infos) {
        this.context = context;
        this.infos = infos;
    }

    @Override
    public int getItemViewType(int position) {
        return infos.get(position).getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case LearnRecordInfo.DATE:
                return new DateViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.item_learn_record_date, parent, false));
            default:
                return new ViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.item_learn_record, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return infos == null ? 0 : infos.size();
    }

    static class DateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.date)
        TextView mDate;

        DateViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon)
        ImageView mIcon;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.author)
        TextView mAuthor;
        @BindView(R.id.time)
        TextView mTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}