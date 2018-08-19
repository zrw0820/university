package com.mysoft.university.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.mysoft.university.R;
import com.mysoft.university.di.component.DaggerCourseDetailComponent;
import com.mysoft.university.di.module.CourseDetailModule;
import com.mysoft.university.mvp.contract.CourseDetailContract;
import com.mysoft.university.mvp.presenter.CourseDetailPresenter;
import com.mysoft.university.mvp.ui.adapter.CourseDetailAdapter;
import com.mysoft.university.mvp.ui.adapter.CourseDirectoryAdapter;
import com.mysoft.university.mvp.ui.adapter.CourseTabAdapter;
import com.mysoft.university.mvp.ui.view.player.StandardVideoAllCallBack;
import com.mysoft.university.mvp.ui.view.player.VideoPlayerOptionBuilder;
import com.mysoft.university.mvp.ui.view.player.VideoPlayerPlus;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 课程详情
 */
public class CourseDetailActivity extends BaseActivity<CourseDetailPresenter> implements CourseDetailContract.View, AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.cover)
    ImageView mCover;
    @BindView(R.id.video_player)
    VideoPlayerPlus mVideoPlayer;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.concise_btn)
    TextView mConciseBtn;

    @BindView(R.id.player_loading)
    TextView mPlayerLoading;

    @BindView(R.id.video_title)
    TextView mVideoTitle;
    @BindView(R.id.play_video)
    LinearLayout mPlayVideo;

    @BindView(R.id.tab_view)
    RecyclerView mTabView;
    @Inject
    RecyclerView.ItemDecoration mTabDecoration;
    @Inject
    CourseTabAdapter mTabAdapter;

    @Inject
    VirtualLayoutManager mLayoutManager;
    @Inject
    DelegateAdapter mDelegateAdapter;

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @Inject
    CourseDirectoryAdapter mDirectoryAdapter;
    @Inject
    CourseDetailAdapter mDetailAdapter;

    private boolean isPlay;
    private boolean isPause;
    private boolean cacheVideo = true;

    private OrientationUtils mOrientationUtils;
    private VideoPlayerOptionBuilder mPlayerOptionBuilder;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCourseDetailComponent
                .builder()
                .appComponent(appComponent)
                .courseDetailModule(new CourseDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_course_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initVideoPlayer();

        ButterKnife.bind(this);

        mAppBarLayout.addOnOffsetChangedListener(this);

        mTabView.addItemDecoration(mTabDecoration);
        mTabView.setAdapter(mTabAdapter);

        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mDelegateAdapter);

        mDelegateAdapter.addAdapter(mDirectoryAdapter);
        mDelegateAdapter.addAdapter(mDetailAdapter);
    }

    private void initVideoPlayer() {
        // 开始播放了才能旋转和全屏
        mOrientationUtils = new OrientationUtils(this, mVideoPlayer);
        mOrientationUtils.setEnable(false);
        mPlayerOptionBuilder = new VideoPlayerOptionBuilder()
                .setUrl("")
                .setEnlargeImageRes(R.drawable.icon_fullscreen)
                .setShrinkImageRes(R.drawable.icon_fullscreen_exit)
                .setIsTouchWidget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1)
                .setCacheWithPlay(cacheVideo)
                .setVideoTitle("")
                .setStandardVideoAllCallBack(new StandardVideoAllCallBack() {
                    @Override
                    public void onPrepared(String s, Object... objects) {
                        // 显示播放器
                        mPlayerLoading.setVisibility(View.GONE);
                        mVideoTitle.setVisibility(View.GONE);
                        mVideoPlayer.setVisibility(View.VISIBLE);
                        // 开始播放了才能旋转和全屏
                        mOrientationUtils.setEnable(true);
                        isPlay = true;
                    }

                    @Override
                    public void onQuitFullscreen(String s, Object... objects) {
                        if (mOrientationUtils != null) {
                            mOrientationUtils.backToProtVideo();
                        }
                    }
                });
        mPlayerOptionBuilder.build(mVideoPlayer);
        mVideoPlayer.getFullscreenButton().setOnClickListener(v -> {
            // 直接横屏
            mOrientationUtils.resolveByClick();
            // 第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            mVideoPlayer.startWindowFullscreen(this, true, true);
        });
        mVideoPlayer.setLockClickListener((view, lock) -> {
            if (mOrientationUtils != null) {
                //配合下方的onConfigurationChanged
                mOrientationUtils.setEnable(!lock);
            }
        });
        mVideoPlayer.getBackButton().setVisibility(View.GONE);
        mVideoPlayer.setOnWidgetVisibleListener(new VideoPlayerPlus.OnWidgetVisibleListener() {
            @Override
            public void onShow() {
                // 非全屏
                if (!mVideoPlayer.isIfCurrentIsFullscreen()) {
                    mToolbar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onHide() {
                // 非全屏
                if (!mVideoPlayer.isIfCurrentIsFullscreen()) {
                    mToolbar.setVisibility(View.INVISIBLE);
                }
            }
        });
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
        finish();
    }

    @OnClick({R.id.close_btn, R.id.concise_btn, R.id.play_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close_btn:
                onBackPressed();
                break;
            case R.id.concise_btn:

                break;
            case R.id.play_video:
                mAppBarLayout.setExpanded(true, true);
                playVideo();
                break;
        }
    }

    private void playVideo() {
        mPlayerLoading.setVisibility(View.VISIBLE);

        mVideoPlayer.release();
        mPlayerOptionBuilder
                .setUrl("http://cn-sdyt-cu-v-11.acgvideo.com/upgcxcode/97/48/50894897/50894897-1-6.mp4?expires=1534700400&platform=html5&ssig=XF00U7SZTIAY79S20NZXNQ&oi=989525098&nfa=7VMUDqBQpI8VGBbhQ1faUQ==&dynamic=1&hfa=2034938775&hfb=M2Y2ZWYwZjM2YmRiYmY5MDljYTBiOWE2ZmEwYjJmYTM=&trid=4383c8fd45f54d4ba49d795363066daa&nfc=1")// 视频URL
                .setCacheWithPlay(cacheVideo)
                .build(mVideoPlayer);
        mVideoPlayer.postDelayed(() -> mVideoPlayer.startPlayLogic(), 300);

        // 锁定AppBarLayout
        View content = mAppBarLayout.getChildAt(0);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) content.getLayoutParams();
        params.setScrollFlags(0);
        content.setLayoutParams(params);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        float percentage = (float) Math.abs(verticalOffset) / (float) appBarLayout.getTotalScrollRange();
        mConciseBtn.setAlpha(1 - percentage);
        mVideoTitle.setAlpha(1 - percentage);
        mPlayVideo.setTranslationX(percentage * (mToolbar.getLeft() + mToolbar.getWidth() / 2
                - mPlayVideo.getLeft() - mPlayVideo.getWidth() / 2));
    }

    @Override
    public Context context() {
        return this;
    }

    private GSYVideoPlayer getCurPlay() {
        if (mVideoPlayer.getFullWindowPlayer() != null) {
            return mVideoPlayer.getFullWindowPlayer();
        }
        return mVideoPlayer;
    }

    @Override
    public void onBackPressed() {
        if (mOrientationUtils != null) {
            mOrientationUtils.backToProtVideo();
        }

        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }

        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        getCurPlay().onVideoResume();
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onPause() {
        getCurPlay().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onDestroy() {
        if (isPlay) {
            getCurPlay().release();
        }
        if (mOrientationUtils != null) {
            mOrientationUtils.releaseListener();
        }
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 如果旋转了就全屏
        if (isPlay && !isPause) {
            mVideoPlayer.onConfigurationChanged(this, newConfig, mOrientationUtils);
        }
    }
}
