package me.huseyinozer.samplehttpclientwithdi.networking;

import java.util.Map;

/**
 * Created by HuseyinOzer on 29.04.2017.
 */

public interface HttpClient {
    void get(String path, HttpSuccessListener successListener, HttpErrorListener errorListener);
    void post(String path, Map<String, String> params, HttpSuccessListener successListener, HttpErrorListener errorListener);
    void put(String path, Map<String, String> params, HttpSuccessListener successListener, HttpErrorListener errorListener);
    void delete(String path, HttpSuccessListener successListener, HttpErrorListener errorListener);
}
