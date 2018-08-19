package com.mysoft.university.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mysoft.university.R;
import com.mysoft.university.mvp.model.entity.CourseListInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseTabAdapter extends RecyclerView.Adapter<CourseTabAdapter.ViewHolder> {
    private Context context;
    private List<CourseListInfo> infos;

    public CourseTabAdapter(Context context, List<CourseListInfo> infos) {
        this.context = context;
        this.infos = infos;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tab_text)
        TextView mTabText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_course_tab, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setSelected(position == 0);
        ((TextView) holder.itemView).setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return infos == null ? 0 : infos.size();
    }
}