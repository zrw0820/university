package com.mysoft.university.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.mysoft.university.R;
import com.mysoft.university.mvp.ui.entity.PersonalItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zourw on 2018/8/18.
 */
public class PersonalAdapter extends DelegateAdapter.Adapter<PersonalAdapter.ViewHolder> {
    private Context context;
    private List<PersonalItem> items;

    public PersonalAdapter(Context context, List<PersonalItem> items) {
        this.context = context;
        this.items = items;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon)
        ImageView mIcon;
        @BindView(R.id.text)
        TextView mText;
        @BindView(R.id.summary)
        TextView mSummary;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_personal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonalItem item = items.get(position);
        holder.mIcon.setImageResource(item.getIcon());
        holder.mText.setText(item.getText());
        holder.mSummary.setText(TextUtils.isEmpty(item.getSummary()) ? "" : item.getSummary());
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
}
