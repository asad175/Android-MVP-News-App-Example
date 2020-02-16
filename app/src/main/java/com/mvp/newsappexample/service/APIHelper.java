package com.mvp.newsappexample.service;

import androidx.annotation.NonNull;
import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class APIHelper {


    public static <T> void enqueueWith(Call<T> call, final Callback<T> callback) {

        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {

                switch (response.code()) {
                    case HttpURLConnection.HTTP_OK:
                        callback.onResponse(call, response);
                        break;

                    default:
                            callback.onFailure(call, new ApiException("Unable to get data from API"));
                        break;
                }
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    public static <T> void cancel(Call<T> call) {
        if (call == null) return;
            call.cancel();
    }
}