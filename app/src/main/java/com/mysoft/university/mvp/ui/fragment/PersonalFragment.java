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

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.mysoft.university.R;
import com.mysoft.university.di.component.DaggerPersonalComponent;
import com.mysoft.university.di.module.PersonalModule;
import com.mysoft.university.mvp.contract.PersonalContract;
import com.mysoft.university.mvp.presenter.PersonalPresenter;
import com.mysoft.university.mvp.ui.adapter.PersonalAdapter;
import com.mysoft.university.mvp.ui.adapter.PersonalHeaderAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * 我的
 */
public class PersonalFragment extends BaseFragment<PersonalPresenter> implements PersonalContract.View {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    Unbinder unbinder;

    @Inject
    VirtualLayoutManager mLayoutManager;

    @Inject
    DelegateAdapter mDelegateAdapter;
    @Inject
    PersonalHeaderAdapter mHeaderAdapter;
    @Inject
    PersonalAdapter mPersonalAdapter;

    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerPersonalComponent
                .builder()
                .appComponent(appComponent)
                .personalModule(new PersonalModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_personal, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mDelegateAdapter);

        mDelegateAdapter.addAdapter(mHeaderAdapter);
        mDelegateAdapter.addAdapter(mPersonalAdapter);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public Context context() {
        return getContext();
    }
}
