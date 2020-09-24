package com.bspl.gnu;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bspl.gnu.blogerApi.blogerApi;
import com.bspl.gnu.pojoclass.Item;
import com.bspl.gnu.pojoclass.PojoclassList;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
adopter adopter;
String nextpageToken="";
    private ArrayList<PojoclassList> data;
     ArrayList<PojoclassList> rsp;
    private sorce sorces;
boolean scrollstate=false;
    int currentItems, totalItems, scrollOutItems;
    LinearLayoutManager Manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.Recycleview);
        data = new ArrayList<>();
        rsp=new ArrayList<>();
        //323756443544-mvan9mgrk6fuh8pfunr747fn8h4npirb.apps.googleusercontent.com
        //AIzaSyCcoWSwsF8noBi5OnRCKme1mm1M45bqB14
        getdata();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    scrollstate=true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = Manager.getChildCount();
                totalItems = Manager.getItemCount();
                scrollOutItems = Manager.findFirstVisibleItemPosition();
                if(scrollstate && (currentItems + scrollOutItems == totalItems))
                {
                    scrollstate = false;
                    getdata();
                }

            }
        });
    }
    private void getdata(){
        String Url=blogerApi.url+"?key="+blogerApi.blogKey;
        if(nextpageToken!=""){
            Url=Url+"&pageToken="+nextpageToken;
        }
        if(nextpageToken==null){
            return;
        }
        final Call<PojoclassList> postlist= blogerApi.getPostServic().getpostlist(Url);
        postlist.enqueue(new Callback<PojoclassList>() {
            @Override
            public void onResponse(Call<PojoclassList> call, Response<PojoclassList> response) {
                PojoclassList jsonArray=response.body();
                nextpageToken=jsonArray.getNextPageToken();
                listdata(jsonArray);


               // Toast.makeText(MainActivity.this, "Succes"+list.getNextPageToken(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PojoclassList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void listdata(PojoclassList list) {

        Log.e("rsp",list.getItems().get(1).getTitle());
        data.add(list);
         Manager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(Manager);
        adopter customAdapter = new adopter(MainActivity.this, list);
        recyclerView.setAdapter(customAdapter);

    }

}