package com.escmobile.okhttpsample;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by genctasbasi on 11/04/15.
 */
public class OkHttpHandler extends AsyncTask<Void, Void, byte[]> {

    // TODO: replace with your own image url
    private final String IMAGE_URL = "http://bit.ly/1DU2Zka";

    OkHttpClient httpClient = new OkHttpClient();

    @Override
    protected byte[] doInBackground(Void... params) {

        Request.Builder builder = new Request.Builder();
        builder.url(IMAGE_URL);

        Request request = builder.build();

        try {

            Response response = httpClient.newCall(request).execute();
            return response.body().bytes();

        } catch (Exception e) {
        }

        return null;
    }
}
