package com.mysoft.university.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.mysoft.university.R;
import com.mysoft.university.mvp.model.entity.CourseDirectory;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 课程目录
 */
public class CourseDirectoryAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CourseDirectory> directories;

    public CourseDirectoryAdapter(Context context, List<CourseDirectory> directories) {
        this.context = context;
        this.directories = directories;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    private static final int HEADER = 0;
    private static final int FOOTER = 1;
    private static final int ITEM = 2;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else if (position == getItemCount() - 1) {
            return FOOTER;
        } else {
            return ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER:
                return new RecyclerView.ViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_course_directory_header, parent, false)) {
                };
            case FOOTER:
                return new FooterViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_course_directory_footer, parent, false)
                );
            case ITEM:
            default:
                return new ViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_course_directory, parent, false)
                );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HEADER:
                break;
            case FOOTER:

                break;
            case ITEM:
                ((ViewHolder) holder).mNo.setText(String.format(Locale.CHINA, "%d.", position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return directories == null ? 0 : directories.size();
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.check_all)
        TextView mCheckAll;

        FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.no)
        TextView mNo;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.time)
        TextView mTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}