package com.mysoft.university.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.mysoft.university.R;
import com.mysoft.university.di.component.DaggerClassComponent;
import com.mysoft.university.di.module.ClassModule;
import com.mysoft.university.mvp.contract.ClassContract;
import com.mysoft.university.mvp.presenter.ClassPresenter;
import com.mysoft.university.mvp.ui.adapter.PageAdapter;
import com.mysoft.university.mvp.ui.view.ClearEditText;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ClassFragment extends BaseFragment<ClassPresenter> implements ClassContract.View {

    @BindView(R.id.search_edit)
    ClearEditText mSearchEdit;
    @BindView(R.id.banner)
    ImageView mBanner;
    @BindView(R.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    Unbinder unbinder;

    @Inject
    List<Fragment> mFragments;
    @Inject
    PageAdapter mPageAdapter;

    public static ClassFragment newInstance() {
        ClassFragment fragment = new ClassFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerClassComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .classModule(new ClassModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_class, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mViewPager.setAdapter(mPageAdapter);
        mTabLayout.setViewPager(mViewPager);
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
    public void killMyself() {

    }

    @Override
    public ClassFragment getFragment() {
        return this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
