package com.ktck124.lop124LTDD04.nhom18.Adapter;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ktck124.lop124LTDD04.nhom18.R;

public class SliderAdapter extends PagerAdapter {
    int[] images;
    LayoutInflater layoutInflater;
    Context context;

    public SliderAdapter(int[] images, Context context) {
        this.images = images;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @NonNull
    @Override

    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View myImageLayout = layoutInflater.inflate(R.layout.slide, container, false);
        ImageView imageview = myImageLayout.findViewById(R.id.imageview);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageview.setImageDrawable(context.getDrawable(images[position]));
        } else {
            imageview.setImageDrawable(context.getResources().getDrawable(images[position]));
        }

        container.addView(myImageLayout);

        return myImageLayout;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
