package me.huseyinozer.samplehttpclientwithdi.networking;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by HuseyinOzer on 29.04.2017.
 */

public class DefaultHttpClient implements HttpClient {

    private final String baseUrl;
    private final RequestQueue requestQueue;

    public DefaultHttpClient(Context context, String baseUrl) {
        requestQueue = Volley.newRequestQueue(context);
        this.baseUrl = baseUrl;
    }

    @Override
    public void get(String path, HttpSuccessListener successListener, HttpErrorListener errorListener) {
        request(Request.Method.GET, path, null, successListener, errorListener);
    }

    @Override
    public void post(String path, Map<String, String> params, HttpSuccessListener successListener, HttpErrorListener errorListener) {
        request(Request.Method.POST, path, params, successListener, errorListener);
    }

    @Override
    public void put(String path, Map<String, String> params, HttpSuccessListener successListener, HttpErrorListener errorListener) {
        request(Request.Method.PUT, path, params, successListener, errorListener);
    }

    @Override
    public void delete(String path, HttpSuccessListener successListener, HttpErrorListener errorListener) {
        request(Request.Method.DELETE, path, null, successListener, errorListener);
    }

    private void request(int method, String path, Map<String, String> params, final HttpSuccessListener successListener, final HttpErrorListener errorListener) {

        // Initialize url with path
        String url = baseUrl + path;

        DefaultRequest request = new DefaultRequest(
                method,
                url,
                response -> {
                    // Send response to listener
                    successListener.onSuccess(response);
                },
                error -> {

                    int statusCode = -1;
                    String errorMessage = null;

                    NetworkResponse networkResponse = error.networkResponse;

                    if (networkResponse != null) {
                        statusCode = networkResponse.statusCode;

                        // If response data is not null, convert response data to string
                        if (networkResponse.data != null) {
                            errorMessage = new String(networkResponse.data);
                        }
                        // If response data is null, get exception message
                        else {
                            errorMessage = error.getMessage();
                        }
                    }

                    // Send error to listener
                    errorListener.onError(statusCode, errorMessage, error);
                }
        );

        // Set request body params
        request.setParams(params);

        // Execute request
        requestQueue.add(request);
    }

    // Default request with params
    private class DefaultRequest extends StringRequest {

        private Map<String, String> params;

        DefaultRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return params;
        }

        void setParams(Map<String, String> params) {
            this.params = params;
        }
    }
}
