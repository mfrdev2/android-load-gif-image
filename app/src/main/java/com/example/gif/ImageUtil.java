package com.example.gif;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class ImageUtil {
    public static final String TAG = "ImageUtil";
    private GifImageAnimListener gifImageAnimListener = null;

    public interface GifImageAnimListener {
        void onAnimationStart(Drawable drawable);

        void onAnimationEnd(Drawable drawable);
    }

    public void loadGifImage(ImageView view, Object img, GifImageAnimListener animListener) {
        this.gifImageAnimListener = animListener;

        Glide.with(view)
                .asGif()
                .load(img)
                .centerCrop()
                .addListener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        resource.setLoopCount(1);
                        resource.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                            @Override
                            public void onAnimationStart(Drawable drawable) {
                                super.onAnimationStart(drawable);
                                ImageUtil.this.gifImageAnimListener.onAnimationStart(drawable);
                            }

                            @Override
                            public void onAnimationEnd(Drawable drawable) {
                                super.onAnimationEnd(drawable);
                                ImageUtil.this.gifImageAnimListener.onAnimationEnd(drawable);
                            }
                        });

                        return false;
                    }
                })
                .into(view);
    }
}
