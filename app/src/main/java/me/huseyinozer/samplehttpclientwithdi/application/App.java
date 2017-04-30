package me.huseyinozer.samplehttpclientwithdi.application;

import android.app.Application;

/**
 * Created by HuseyinOzer on 29.04.2017.
 */

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Application Component
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
