package cn.everelegance.ugank.network;

import cn.everelegance.ugank.network.api.GankApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/8/4.
 */

public class NetWork {
    private static GankApi gankApi ;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    public static GankApi getGankApi(){

        if(gankApi == null){
             gankApi = new Retrofit.Builder()
                    .baseUrl("http://gank.io/api/")
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(GankApi.class);
        }
        return gankApi;
    }

}

