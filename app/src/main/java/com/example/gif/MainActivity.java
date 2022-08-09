package com.example.gif;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.gif.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private ImageUtil imageUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        imageUtil = new ImageUtil();
        loadImage();
    }

    private void loadImage() {
        imageUtil.loadGifImage(binding.vImg, R.drawable.gas_monkey_logo_once, new ImageUtil.GifImageAnimListener() {
            @Override
            public void onAnimationStart(Drawable drawable) {
                Log.d(TAG, "AnimStart");
            }

            @Override
            public void onAnimationEnd(Drawable drawable) {
                Log.d(TAG, "AnimEnd");
                redirectToFullscreenActivity();
            }
        });
    }

    private void redirectToFullscreenActivity() {
        startActivity(new Intent(this, FullscreenActivity.class));
        finish();
    }
}