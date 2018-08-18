package com.mysoft.university.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.mysoft.university.R;
import com.mysoft.university.di.component.DaggerClassChildComponent;
import com.mysoft.university.di.module.ClassChildModule;
import com.mysoft.university.mvp.contract.ClassChildContract;
import com.mysoft.university.mvp.model.entity.ClassInfo;
import com.mysoft.university.mvp.presenter.ClassChildPresenter;
import com.mysoft.university.mvp.ui.adapter.IndexClassAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ClassChildFragment extends BaseFragment<ClassChildPresenter> implements ClassChildContract.View {
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    Unbinder unbinder;

    @Inject
    List<ClassInfo> mInfos;
    @Inject
    IndexClassAdapter mAdapter;

    public static ClassChildFragment newInstance() {
        ClassChildFragment fragment = new ClassChildFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerClassChildComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .classChildModule(new ClassChildModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_class_child, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
