package com.example.admin.isspasstimes.view.main;

import android.location.Location;

import com.example.admin.isspasstimes.model.Response;
import com.example.admin.isspasstimes.view.BasePresenter;
import com.example.admin.isspasstimes.view.BaseView;

import java.util.List;

/**
 * Created by admin on 9/26/2017.
 */

public interface MainActivityContract {
    interface  View extends BaseView {
        void updateView(String s);

        void updateRV(List<Response> children);
    }

    interface Presenter extends BasePresenter<View> {
        void validateInput(String inputString);

        void updateCall(Location query);
    }

}
