package com.example.admin.isspasstimes.view;

/**
 * Created by admin on 9/26/2017.
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);
    void detachView();

}
