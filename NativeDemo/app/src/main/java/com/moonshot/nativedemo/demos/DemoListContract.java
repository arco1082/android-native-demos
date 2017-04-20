package com.moonshot.nativedemo.demos;

import android.support.annotation.NonNull;
import android.view.View;

import com.moonshot.nativedemo.BasePresenter;
import com.moonshot.nativedemo.BaseView;
import com.moonshot.nativedemo.data.models.Demo;

import java.util.List;

/**
 * Created by armando_contreras on 4/20/17.
 */

public interface DemoListContract {

    interface DemoView extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showDemoList(List<Demo> demo);

        void showDemoDetailsUI(View view, Demo demo);

        void showLoadingError();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void loadDemos();

        void openDemoDetails(View view, @NonNull Demo demo);

        void onError();

    }

}