package me.huseyinozer.samplehttpclientwithdi.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.huseyinozer.samplehttpclientwithdi.networking.DefaultHttpClient;
import me.huseyinozer.samplehttpclientwithdi.networking.HttpClient;

/**
 * Created by HuseyinOzer on 30.04.2017.
 */

// Like AppAssembly
@Module
public class ApplicationModule {

    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public Context context() {
        return app.getApplicationContext();
    }

    @Singleton
    @Provides
    public HttpClient httpClient(Context context) {
        return new DefaultHttpClient(context, "http://www.mocky.io/v2/");
    }

}
