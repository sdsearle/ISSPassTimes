package com.example.admin.isspasstimes.view.main;


import android.location.Location;
import android.util.Log;

import com.example.admin.isspasstimes.RetrofitHelper;
import com.example.admin.isspasstimes.model.ISSResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by admin on 9/26/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private static final String TAG = "MAPresenter";
    MainActivityContract.View view;
    List<com.example.admin.isspasstimes.model.Response> issResponses;

    private Observer<Response<ISSResponse>> ISSObserver = new Observer<Response<ISSResponse>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Response<ISSResponse> issResponseResponse) {
            issResponses = issResponseResponse.body().getResponse();
            Log.d(TAG, "onNext: " + issResponseResponse.raw().request().url().toString());
            Log.d(TAG, "onNext: " + issResponses.size());
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError: " + e.getMessage());
        }

        @Override
        public void onComplete() {
            view.updateRV(issResponses);
        }
    };


    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }


    @Override
    public void validateInput(String inputString) {

    }

    @Override
    public void updateCall(Location location) {
        String lat = location.getLatitude() + "";
        String lng = location.getLongitude() + "";
        Observable<Response<ISSResponse>> responseObservable = RetrofitHelper.createSearch(lat,lng);
        responseObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(ISSObserver);
    }
}
