package com.bspl.gnu;

import android.app.Activity;
import android.text.Html;
import android.text.Spannable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bspl.gnu.pojoclass.PojoclassList;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class adopter extends RecyclerView.Adapter<adopter.MyViewholder> {
   // ArrayList<PojoclassList> list;
    PojoclassList list;
    Activity activity;
    List<sorce> sorces=new ArrayList<>();

    public adopter(MainActivity mainActivity, PojoclassList list) {

        this.activity=mainActivity;
        this.list=list;
       // this.sorces=sorces;
      //  this.urlimg=urlimg;
    }


    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.newslayout,parent,false);
        return new adopter.MyViewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
//holder.titel.setText(list.getItems().get(position).getContent());
       String text= list.getItems().get(position).getContent();
        holder.titel.setText(text);
        PicassoImageGetter imageGetter = new PicassoImageGetter((MainActivity) activity,holder.titel);
        Spannable html;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            html = (Spannable) Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY, imageGetter, null);

        } else {
            html = (Spannable) Html.fromHtml(text, imageGetter, null);
        }
        holder.titel.setText(html);
        holder.content.setText(list.getItems().get(position).getTitle());
//        Picasso.get().load(sorces.getUrlimg())
//                .error(R.mipmap.ic_launcher).into(holder.imageView, new Callback() {
//            @Override
//            public void onSuccess() {
//                Log.d("TAG", "onSuccess");
//            }
//
//            @Override
//            public void onError(Exception e) {
//                Toast.makeText(activity, "An error occurred", Toast.LENGTH_SHORT).show();
//
//            }
//
//
//        });

    }

    @Override
    public int getItemCount() {
        if(list != null){

            return list.getItems().size()  ;
        }
        return 0;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titel,content;
        public MyViewholder(@NonNull View itemView) {

            super(itemView);
            //imageView=itemView.findViewById(R.id.image);
            titel=itemView.findViewById(R.id.titel);
            content=itemView.findViewById(R.id.content);
//            for(int i=0;i<=sorces.size()-1;i++){
//                Picasso.get().load(sorces.get(i).getUrlimg())
//                        .error(R.mipmap.ic_launcher).into(imageView, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        Log.d("TAG", "onSuccess");
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        Toast.makeText(activity, "An error occurred", Toast.LENGTH_SHORT).show();
//
//                    }
//
//
//                });
//
//            }
        }

    }
    public  void adptersorce(Activity activity, ArrayList arrayList) {
        this.activity=activity;
        this.sorces=arrayList;

    }

}
