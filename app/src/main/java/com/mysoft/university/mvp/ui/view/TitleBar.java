package com.mysoft.university.mvp.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mysoft.university.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zourw on 2018/8/18.
 */
public class TitleBar extends RelativeLayout {
    @BindView(R.id.back_btn)
    TextView mBackBtn;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.action_btn)
    TextView mActionBtn;
    @BindView(R.id.divider)
    View mDivider;

    private OnBackClickListener mOnBackClickListener;
    private OnActionClickListener mOnActionClickListener;

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        inflate(context, R.layout.view_title_bar, this);
        ButterKnife.bind(this);

        TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        String title = tArray.getString(R.styleable.TitleBar_title);
        String backText = tArray.getString(R.styleable.TitleBar_backText);
        String actionText = tArray.getString(R.styleable.TitleBar_actionText);
        boolean showDivider = tArray.getBoolean(R.styleable.TitleBar_showDivider, true);
        tArray.recycle();

        mTitle.setText(TextUtils.isEmpty(title) ? "" : title);
        mBackBtn.setText(TextUtils.isEmpty(backText) ? "返回" : backText);
        mActionBtn.setText(TextUtils.isEmpty(actionText) ? "" : actionText);
        mActionBtn.setVisibility(TextUtils.isEmpty(actionText) ? GONE : VISIBLE);
        mDivider.setVisibility(showDivider ? VISIBLE : GONE);
    }

    @OnClick({R.id.back_btn, R.id.action_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                if (mOnBackClickListener != null) {
                    mOnBackClickListener.onBackClick(this, view);
                }
                break;
            case R.id.action_btn:
                if (mOnActionClickListener != null) {
                    mOnActionClickListener.onActionClick(this, view);
                }
                break;
        }
    }

    public interface OnBackClickListener {
        void onBackClick(TitleBar bar, View back);
    }

    public interface OnActionClickListener {
        void onActionClick(TitleBar bar, View action);
    }

    public void setOnBackClickListener(OnBackClickListener onBackClickListener) {
        mOnBackClickListener = onBackClickListener;
    }

    public void setOnActionClickListener(OnActionClickListener onActionClickListener) {
        mOnActionClickListener = onActionClickListener;
    }
}