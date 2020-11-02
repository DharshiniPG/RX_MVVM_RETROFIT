package com.example.rx_mvvm_retrofit.viewmodal;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class MoviesCardViewItemViewModal {
    private String name;
    private Bitmap bitmap;
    private Resources resources;

    public MoviesCardViewItemViewModal(String name, Bitmap bitmap, Resources resources) {
        this.name = name;
        this.bitmap = bitmap;
        this.resources = resources;
    }

    public String getName() { return name; }

    public BitmapDrawable getBitmap() { return new BitmapDrawable(resources, bitmap); }
}
