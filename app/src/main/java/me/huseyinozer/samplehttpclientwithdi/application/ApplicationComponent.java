package me.huseyinozer.samplehttpclientwithdi.application;

import javax.inject.Singleton;

import dagger.Component;
import me.huseyinozer.samplehttpclientwithdi.screens.main.MainActivity;

/**
 * Created by HuseyinOzer on 30.04.2017.
 */

@Singleton
@Component(
        modules = ApplicationModule.class
)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
