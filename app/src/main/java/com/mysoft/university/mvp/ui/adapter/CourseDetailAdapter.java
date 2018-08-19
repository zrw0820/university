package com.mysoft.university.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.jess.arms.utils.ArmsUtils;
import com.mysoft.university.R;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 课程详情
 */
public class CourseDetailAdapter extends DelegateAdapter.Adapter<CourseDetailAdapter.ViewHolder> {
    private Context context;
    private List<String> data;

    public CourseDetailAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.course_no)
        TextView mCourseNo;
        @BindView(R.id.course_title)
        TextView mCourseTitle;
        @BindView(R.id.cover)
        ImageView mCover;
        @BindView(R.id.last_btn)
        ImageView mLastBtn;
        @BindView(R.id.no_text)
        TextView mNoText;
        @BindView(R.id.next_btn)
        ImageView mNextBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        LinearLayoutHelper helper = new LinearLayoutHelper();
        helper.setDividerHeight(ArmsUtils.dip2px(context, 6));
        return helper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_course_detail, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mCourseNo.setText(String.format(Locale.CHINA, "课程%s", position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}