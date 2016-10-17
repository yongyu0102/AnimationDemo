package com.example.zhangpeng.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

/**
 * Created by zhangpeng on 2016/10/5.
 */
public class DrawbleDemo {
    private Context context;
    private ImageView imageView;

    public DrawbleDemo(Context context){
        this.context=context;
    }

    public void test(){
        BitmapDrawable bitmapDrawable=new BitmapDrawable(context.getResources());
    }
}
