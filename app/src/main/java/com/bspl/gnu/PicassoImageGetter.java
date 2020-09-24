package com.bspl.gnu;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;


public class PicassoImageGetter implements Html.ImageGetter {

    private TextView textView = null;
    Activity  activity;

public ArrayList arrayList;

    public PicassoImageGetter(MainActivity activity, TextView target) {
        textView = target;
       this.activity=activity;
        Log.e("rsp",target.toString());
    }

    @Override
    public Drawable getDrawable(String source) {

        arrayList=new ArrayList();
        arrayList.add(source);
       if(arrayList!=null){

           new sorce(activity,arrayList);
//adptersorce(activity,arrayList);
       }
        //sorce= new sorce(source);


        BitmapDrawablePlaceHolder drawable = new BitmapDrawablePlaceHolder();
        Picasso.get()
                .load(source)
                .placeholder(R.drawable.ic_launcher_background)
                .into(drawable);
        return drawable;
    }

    private class BitmapDrawablePlaceHolder extends BitmapDrawable implements Target {

        protected Drawable drawable;

        @Override
        public void draw(final Canvas canvas) {
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
            Log.e("rsp",drawable.toString());

            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            drawable.setBounds(0, 0, width, height);

            setBounds(0, 0, 500, 200);
            if (textView != null) {
                textView.setText(textView.getText());
            }
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            setDrawable(new BitmapDrawable(activity.getResources(), bitmap));
          //  Toast.makeText(activity, ""+from, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            Toast.makeText(activity, ""+errorDrawable, Toast.LENGTH_SHORT).show();
        }


        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

          //  Toast.makeText(activity, ""+placeHolderDrawable, Toast.LENGTH_SHORT).show();

        }

    }
}
