package me.huseyinozer.samplehttpclientwithdi.networking;

/**
 * Created by HuseyinOzer on 30.04.2017.
 */

public interface HttpErrorListener {
    void onError(int statusCode, String errorMessage, Throwable error);

}
