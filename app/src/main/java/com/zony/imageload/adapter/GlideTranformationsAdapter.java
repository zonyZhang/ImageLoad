package com.zony.imageload.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zony.imageload.R;
import com.zony.imageload.utils.ImgLoadUtil;
import com.zony.imageloadlib.options.ImgLoaderOptions;
import com.zony.imageloadlib.transform.GlideCircleTransform;
import com.zony.imageloadlib.transform.GlideRotateTransformation;
import com.zony.imageloadlib.transform.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

public class GlideTranformationsAdapter extends RecyclerView.Adapter<GlideTranformationsAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mData = new ArrayList<>();
    private ImgLoaderOptions options;

    public GlideTranformationsAdapter(Context context) {
        mContext = context;
        for (int i = 1; i <= 24; i++) {
            mData.add(i + "");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = View.inflate(mContext, R.layout.item_glide_tranformations, null);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 设置名称
        holder.name.setText("POSITION:" + (position + 1));
        int integer = Integer.parseInt(mData.get(position));
        switch (integer) {
            case 1: {
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .diskCacheStrategy(ImgLoaderOptions.DiskCache.SOURCE)
                        .transforms(new MaskTransformation(R.drawable.mask_starfish),
                                new GlideRotateTransformation(60),
                                new ColorFilterTransformation(0x7900CCCC))
                        .skipMemoryCache(false)
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            }
            case 2: {
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new MaskTransformation(R.drawable.mask_chat_right))
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            }
            case 3:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new CropTransformation(300, 100, CropTransformation.CropType.TOP))
                        .build();
                ImgLoadUtil.showImage(R.drawable.demo, holder.image, options);
                break;
            case 4:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new CropTransformation(300, 100))
                        .build();
                ImgLoadUtil.showImage(R.drawable.demo, holder.image, options);
                break;
            case 5:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new CropTransformation(300, 100, CropTransformation.CropType.BOTTOM))
                        .build();
                ImgLoadUtil.showImage(R.drawable.demo, holder.image, options);
                break;
            case 6:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new CropSquareTransformation())
                        .build();
                ImgLoadUtil.showImage(R.drawable.demo, holder.image, options);
                break;
            case 7:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new CropCircleTransformation())
                        .build();
                ImgLoadUtil.showImage(R.drawable.demo, holder.image, options);
                break;
            case 8:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new ColorFilterTransformation(Color.argb(80, 255, 0, 0)))
                        .build();
                ImgLoadUtil.showImage(R.drawable.demo, holder.image, options);
                break;
            case 9:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new GrayscaleTransformation())
                        .build();
                ImgLoadUtil.showImage(R.drawable.demo, holder.image, options);
                break;
            case 10:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new RoundedCornersTransformation(30, 0,
                                RoundedCornersTransformation.CornerType.BOTTOM))
                        .build();
                ImgLoadUtil.showImage(R.drawable.demo, holder.image, options);
                break;
            case 11:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new BlurTransformation(25))
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            case 12:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .diskCacheStrategy(ImgLoaderOptions.DiskCache.SOURCE)
                        .transform(new ToonFilterTransformation())
                        .build();
                ImgLoadUtil.showImage(R.drawable.demo, holder.image, options);
                break;
            case 13:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new ColorFilterTransformation(Color.argb(80, 255, 0, 0)))
                        .build();
                ImgLoadUtil.showImage(R.drawable.demo, holder.image, options);
                break;
            case 14:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new ContrastFilterTransformation(2.0f))
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            case 15:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new InvertFilterTransformation())
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            case 16:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new PixelationFilterTransformation(20))
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            case 17:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new SketchFilterTransformation())
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            case 18:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(
                                new SwirlFilterTransformation(0.5f, 1.0f, new PointF(0.5f, 0.5f)))
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            case 19:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new BrightnessFilterTransformation(0.5f))
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            case 20:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new KuwaharaFilterTransformation(25))
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            case 21:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                                new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f))
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            case 22:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new GlideCircleTransform())
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            case 23:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new GlideRotateTransformation(3f))
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            case 24:
                options = new ImgLoaderOptions.Builder()
                        .errorId(R.mipmap.ic_launcher)
                        .placeholderId(R.mipmap.ic_launcher)
                        .transform(new GlideRoundTransform(10))
                        .build();
                ImgLoadUtil.showImage(R.drawable.check, holder.image, options);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_glide_tranfromations);
            name = (TextView) itemView.findViewById(R.id.tv_glide_name);
        }
    }
}
