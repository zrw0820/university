package com.mysoft.university.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mysoft.university.R;
import com.mysoft.university.mvp.model.entity.PracticeInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zourw on 2018/8/18.
 */
public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ViewHolder> {
    private Context context;
    private List<PracticeInfo> infos;

    public PracticeAdapter(Context context, List<PracticeInfo> infos) {
        this.context = context;
        this.infos = infos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_practice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return infos == null ? 0 : infos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content)
        TextView mContent;
        @BindView(R.id.author)
        TextView mAuthor;
        @BindView(R.id.date)
        TextView mDate;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
