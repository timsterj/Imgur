package com.timsterj.imgur;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.multidex.MultiDex;

import com.timsterj.imgur.di.AppComponent;
import com.timsterj.imgur.di.DaggerAppComponent;
import com.timsterj.imgur.di.HomeComponent;

public class App extends Application {

    private static App INSTANCE;

    private AppComponent appComponent;
    private HomeComponent homeComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        appComponent = DaggerAppComponent.builder()
                .context(getApplicationContext())
                .build();


    }

    public static App getINSTANCE() {
        return INSTANCE;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


    public HomeComponent getHomeComponent() {
        if (homeComponent == null) {
            homeComponent = appComponent.plusHomeComponent();
        }
        return homeComponent;
    }

    public void clearHomeComponent(){
        homeComponent = null;
    }

}
