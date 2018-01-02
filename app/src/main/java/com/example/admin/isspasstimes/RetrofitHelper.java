package com.example.admin.isspasstimes;

import com.example.admin.isspasstimes.model.ISSResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by admin on 11/6/2017.
 */

public class RetrofitHelper {

    private final static String BASE_URL = "http://api.open-notify.org/";

    public static Retrofit create(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Observable<Response<ISSResponse>> createSearch(int lat, int lon){
        Retrofit retrofit = create();
        ApiService apiService =  retrofit.create(ApiService.class);
        return apiService.getSearch(lat, lon);

    }

    interface ApiService{

        //search?q=chicken&app_id=1f4aae74&app_key=edc70eb534f8c9850813717f88471596
        @GET("iss-pass.json?")
        Observable<Response<ISSResponse>> getSearch(@Query("lat") int lat, @Query("lon") int lon);
    }

}
