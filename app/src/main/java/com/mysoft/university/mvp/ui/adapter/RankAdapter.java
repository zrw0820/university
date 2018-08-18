package com.mysoft.university.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysoft.university.R;
import com.mysoft.university.mvp.model.entity.RankInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 排行榜
 * <p>
 * Created by Zourw on 2018/8/18.
 */
public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
    private Context context;
    private List<RankInfo> infos;

    public RankAdapter(Context context, List<RankInfo> infos) {
        this.context = context;
        this.infos = infos;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.serial_no)
        TextView mSerialNo;
        @BindView(R.id.portrait)
        ImageView mPortrait;
        @BindView(R.id.username)
        TextView mUsername;
        @BindView(R.id.time)
        TextView mTime;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private static final int TOP_FIRST = 0;
    private static final int TOP_SECOND = 1;
    private static final int TOP_OTHER = 2;

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TOP_FIRST;
            case 1:
                return TOP_SECOND;
            default:
                return TOP_OTHER;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rank, parent, false);
        ViewHolder holder = new ViewHolder(view);

        switch (viewType) {
            case TOP_FIRST:
                holder.itemView.setBackgroundResource(R.drawable.shadow_blue);
                holder.itemView.setScaleX(1.06f);
                holder.itemView.setScaleY(1.06f);
                holder.mSerialNo.setTextColor(Color.WHITE);
                holder.mUsername.setTextColor(Color.WHITE);
                holder.mTime.setTextColor(Color.WHITE);
                break;
            case TOP_SECOND:
                holder.itemView.setBackgroundResource(R.drawable.shadow_blue_tint);
                holder.itemView.setScaleX(1.03f);
                holder.itemView.setScaleY(1.03f);
                holder.mSerialNo.setTextColor(Color.WHITE);
                holder.mUsername.setTextColor(Color.WHITE);
                holder.mTime.setTextColor(Color.WHITE);
                break;
            default:
                holder.itemView.setBackgroundResource(R.drawable.shadow_white);
                holder.itemView.setScaleX(1.0f);
                holder.itemView.setScaleY(1.0f);
                holder.mSerialNo.setTextColor(Color.parseColor("#4A4A4A"));
                holder.mUsername.setTextColor(Color.parseColor("#4A4A4A"));
                holder.mTime.setTextColor(Color.parseColor("#BDBDBD"));
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return infos == null ? 0 : infos.size();
    }
}