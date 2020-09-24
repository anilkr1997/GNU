package com.bspl.gnu;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class sorce  {
    String urlimg;
   public static ArrayList<String> arrPackage;
    SharedPreferences sp ;
    public sorce(Activity activity, ArrayList urlimg) {

        for(int i=0;i<=urlimg.size()-1;i++){

            setUrlimg(urlimg.get(i).toString());
        }

    }

    public String getUrlimg() {
        return urlimg;
    }

    public void setUrlimg(String urlimg) {
        this.urlimg = urlimg;
        Log.e("rsp1",urlimg);
    }
}
