package com.zony.imageload.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zony.imageload.R;
import com.zony.imageload.utils.ImgLoadUtil;
import com.zony.imageload.utils.LogUtil;
import com.zony.imageloadlib.listener.ImgLoaderListener;
import com.zony.imageloadlib.options.ImgLoaderOptions;

public class GlideBaseFragment extends Fragment {
    private static final String TAG = "GlideBaseFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView tvGlide1;
    ImageView ivGlide1;
    TextView tvGlide2;
    ImageView ivGlide2;
    TextView tvGlide3;
    ImageView ivGlide3;
    TextView tvGlide4;
    ImageView ivGlide4;
    TextView tvGlide5;
    ImageView ivGlide5;
    TextView tvGlide6;
    ImageView ivGlide6;
    TextView tvGlide7;
    ImageView ivGlide7;
    TextView tvGlide8;
    ImageView ivGlide8;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GlideBaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment using the
     * provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GlideBaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GlideBaseFragment newInstance(String param1, String param2) {
        GlideBaseFragment fragment = new GlideBaseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_glide_base, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        ImgLoaderOptions options = ImgLoadUtil.getOptions(R.mipmap.ic_launcher);
        Object path = "http://img.shouji.baofeng.com/img/741/nl3741_short3933_448*252.222.jpg";

        // （1）加载网络图片
        tvGlide1.setText("（1）加载网络图片");
        changeImage(path, ivGlide1, options);
        LogUtil.i(TAG, "GlideBaseFragment getCacheFile :" + ImgLoadUtil.getCacheFile(getActivity(), path));

        // （2）加载资源图片
        tvGlide2.setText("（2）加载资源图片");
        path = R.drawable.local;
        changeImage(path, ivGlide2, options);

        // （3）加载本地图片
        tvGlide3.setText("（3）加载本地图片");
        path = Environment.getExternalStorageDirectory() + "/Pictures/timgp.jpg";
        changeImage(path, ivGlide3, options);

        // （4）加载网络gif
        tvGlide4.setText("（4）加载网络gif");
        path =
                "http://img.shouji.baofeng.com/img/741/nl3741_short5292_448*252.51.gif";
        path = "http://p2pmid.baofeng.com/160705/20160705073003255.gif";
        changeImage(path, ivGlide4, options);

        // （5）加载资源gif
        tvGlide5.setText("（5）加载资源gif");
        path = R.drawable.localgif;
        changeImage(path, ivGlide5, options);

        // （6）加载本地gif
        tvGlide6.setText("（6）加载本地gif");// sd卡
        path = Environment.getExternalStorageDirectory() +
                "/test/pic/20160705073003255.gif";
        path = Environment.getExternalStorageDirectory() + "/Pictures/timg.gif";
        changeImage(path, ivGlide6, options);

        // （7）加载本地小视频和快照
        tvGlide7.setText("（7）加载本地小视频和快照");
        String videoName = "/Movies/songzi.mp4";
        path = Environment.getExternalStorageDirectory() + videoName;
        changeImage(path, ivGlide7, new
                ImgLoaderOptions.Builder().errorId(R.mipmap.ic_launcher)
                .placeholderId(R.mipmap.ic_launcher).diskCacheStrategy(ImgLoaderOptions.DiskCache.RESULT).build());

        // （8）设置缩略图比例,然后，先加载缩略图，再加载原图
        tvGlide8.setText("（8）设置缩略图比例,然后，先加载缩略图，再加载原图");
        path = Environment.getExternalStorageDirectory() + "/Pictures/timgp.jpg";
        changeImage(path, ivGlide8,
                new
                        ImgLoaderOptions.Builder().errorId(R.mipmap.ic_launcher).placeholderId(R.mipmap.ic_launcher)
                        .diskCacheStrategy(ImgLoaderOptions.DiskCache.NONE).thumbnail(0.1f).build());

    }

    private void changeImage(Object path, final ImageView imageView, ImgLoaderOptions options) {
        // ImgLoadUtil.showImage(path, imageView, options);
        ImgLoadUtil.showImage(path, imageView, options, new ImgLoaderListener() {

            @Override
            public void onRequestSuccess(Object resource) {
                LogUtil.i(TAG, "GlideBaseFragment onRequestSuccess");
            }

            @Override
            public void onRequestFailed(Throwable throwable) {
                LogUtil.w(TAG, "GlideBaseFragment onRequestFailed: " + throwable.toString());
            }

            @Override
            public void onLoadStarted() {
                LogUtil.i(TAG, "GlideBaseFragment onLoadStarted");
            }

            @Override
            public void onLoadFailed(Object resource) {
                LogUtil.i(TAG, "GlideBaseFragment onLoadFailed");
            }

            @Override
            public void onLoadSuccess(Object resource) {
                LogUtil.i(TAG, "GlideBaseFragment onLoadSuccess");
            }

            @Override
            public void onLoadCancelled(Object resource) {
                LogUtil.i(TAG, "GlideBaseFragment onLoadCancelled");
            }
        });
    }

    private void initView(View view) {
        tvGlide1 = (TextView) view.findViewById(R.id.tv_glide_1);
        tvGlide2 = (TextView) view.findViewById(R.id.tv_glide_2);
        tvGlide3 = (TextView) view.findViewById(R.id.tv_glide_3);
        tvGlide4 = (TextView) view.findViewById(R.id.tv_glide_4);
        tvGlide5 = (TextView) view.findViewById(R.id.tv_glide_5);
        tvGlide6 = (TextView) view.findViewById(R.id.tv_glide_6);
        tvGlide7 = (TextView) view.findViewById(R.id.tv_glide_7);
        tvGlide8 = (TextView) view.findViewById(R.id.tv_glide_8);
        ivGlide1 = (ImageView) view.findViewById(R.id.iv_glide_1);
        ivGlide2 = (ImageView) view.findViewById(R.id.iv_glide_2);
        ivGlide3 = (ImageView) view.findViewById(R.id.iv_glide_3);
        ivGlide4 = (ImageView) view.findViewById(R.id.iv_glide_4);
        ivGlide5 = (ImageView) view.findViewById(R.id.iv_glide_5);
        ivGlide6 = (ImageView) view.findViewById(R.id.iv_glide_6);
        ivGlide7 = (ImageView) view.findViewById(R.id.iv_glide_7);
        ivGlide8 = (ImageView) view.findViewById(R.id.iv_glide_8);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ImgLoadUtil.clearAll(getActivity());
    }
}
