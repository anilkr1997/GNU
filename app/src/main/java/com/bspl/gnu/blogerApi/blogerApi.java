package com.bspl.gnu.blogerApi;

import com.bspl.gnu.pojoclass.Item;
import com.bspl.gnu.pojoclass.PojoclassList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Url;

public class blogerApi {

    public static PostServic getPostServic(){
if(postServic==null){
    Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
    postServic=retrofit.create(PostServic.class);
}
return postServic;
    }
    public interface PostServic{
        @GET
        Call<PojoclassList> getpostlist(@Url String url);
//        @GET("{postId}/?key"+blogKey)
//        Call<Item> getpostbyid(@Part("postId") String id);

    }

}
