package com.zony.imageload.touchimage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bm.library.PhotoView;
import com.zony.imageload.R;
import com.zony.imageload.utils.ImgLoadUtil;
import com.zony.imageload.utils.LogUtil;
import com.zony.imageloadlib.listener.ImgLoaderListener;
import com.zony.imageloadlib.options.ImgLoaderOptions;


/**
 * 显示图片Fragment
 *
 * @author zony
 * @time 18-7-5
 */
public class ShowPicFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ShowPicFragment";

    public static final String PIC_URL = "pic_url";

    private ProgressBar progressBar;

    private PhotoView photoImg;

    private ImgLoaderOptions options;

    private String mUrl;

    public static ShowPicFragment create(String item) {
        ShowPicFragment fragment = new ShowPicFragment();
        Bundle args = new Bundle();
        args.putString(PIC_URL, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrl = getArguments().getString(PIC_URL);
        options = ImgLoadUtil.getOptions(R.mipmap.ic_launcher);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_showimage, container, false);
        initView(view);
        initData();
        return view;
    }

    public void initView(View view) {
        photoImg = view.findViewById(R.id.show_photo_img);
        photoImg.enable();
        photoImg.setOnClickListener(this);
        progressBar = view.findViewById(R.id.show_picture_progressbar);
    }

    public void initData() {
        showPhotoImg();
    }

    /**
     * 显示图片
     *
     * @author zony
     * @time 18-7-5
     */
    private void showPhotoImg() {
        if (mUrl == null && photoImg != null) {
            LogUtil.i(TAG, "showPhotoImg mUrl is null, return!");
            return;
        }
        LogUtil.i(TAG, "showPhotoImg = " + mUrl);
        ImgLoadUtil.showImage(mUrl, photoImg, options, new ImgLoaderListener() {
            @Override
            public void onLoadStarted() {
                super.onLoadStarted();
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadFailed(Object resource) {
                super.onLoadFailed(resource);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onRequestFailed(Throwable throwable) {

            }

            @Override
            public void onLoadSuccess(Object resource) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadCancelled(Object resource) {
                super.onLoadCancelled(resource);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_photo_img:
                if (getActivity() != null && isAdded()) {
                    getActivity().finish();
                    getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                break;
            default:
                break;
        }
    }
}
