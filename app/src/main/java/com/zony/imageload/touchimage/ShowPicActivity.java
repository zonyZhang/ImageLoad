package com.zony.imageload.touchimage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zony.imageload.R;
import com.zony.imageload.constant.ImageLoadConstant;
import com.zony.imageload.utils.CommonUtils;

import java.util.ArrayList;

/**
 * 显示图片Activity
 *
 * @author zony
 * @time 18-7-5
 */
public class ShowPicActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = "ShowPicActivity";

    private ViewPager viewPager;

    private ShowPicAdapter pagerAdapter;

    /**
     * 图片列表
     */
    private ArrayList<String> mPicList;

    /**
     * 当前图片位置
     */
    private int currentPos;

    /**
     * 指示器控件
     */
    private BezierBannerView bezierBannerView;

    /**
     * 当前展示图片的url
     */
    private String currentPicUrl;

    /**
     * 显示图片
     *
     * @param context  上下文
     * @param position 指定位置
     * @param urlList  地址列表
     */
    public static void start(Context context, @Nullable int position, @NonNull ArrayList<String> urlList) {
        if (urlList == null || urlList.size() == 0) {
            return;
        }
        if (position < 0 || position > urlList.size() - 1) {
            return;
        }
        Intent intent = new Intent(context, ShowPicActivity.class);
        intent.putStringArrayListExtra(ImageLoadConstant.Pic.PICITEMS, urlList);
        intent.putExtra(ImageLoadConstant.Pic.PICITEMS_POSITION, position);
        CommonUtils.startActivity(context, intent, false);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    /**
     * 初始化view
     *
     * @author zony
     * @time 18-7-6
     */
    private void initView() {
        setContentView(R.layout.activity_showimage);
        viewPager = findViewById(R.id.splash_layout_gradient_viewpager);
        pagerAdapter = new ShowPicAdapter(getSupportFragmentManager());
        bezierBannerView = findViewById(R.id.bezierBannerView);

        pagerAdapter.setShowItems(mPicList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(currentPos);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(2);
        bezierBannerView.attachToViewpager(viewPager);

        findViewById(R.id.iv_save_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.savePicToGallery(ShowPicActivity.this, currentPicUrl);
            }
        });
    }

    /**
     * 初始化数据
     *
     * @author zony
     * @time 18-7-6
     */
    private void initData() {
        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                currentPos = bundle.getInt(ImageLoadConstant.Pic.PICITEMS_POSITION);
                mPicList = bundle.getStringArrayList(ImageLoadConstant.Pic.PICITEMS);
            }
        }
        if (mPicList == null) {
            finish();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        currentPicUrl = mPicList.get(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        /*关闭硬件加速*/
        if (state != ViewPager.SCROLL_STATE_IDLE) {
            final int childCount = viewPager.getChildCount();
            for (int i = 0; i < childCount; i++) {
                viewPager.getChildAt(i).setLayerType(View.LAYER_TYPE_NONE, null);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.viewPager.removeOnPageChangeListener(this);
        bezierBannerView = null;
    }


}
