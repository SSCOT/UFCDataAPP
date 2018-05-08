package com.sergio.ufcdataappinicial.ufcdataapp.Data.Providers;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sergio.ufcdataappinicial.ufcdataapp.BuildConfig;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Model.Media.Media;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.GsonRequest;
import com.sergio.ufcdataappinicial.ufcdataapp.Data.Requests.RequestManager;

public class MediaProvider {

    Context context;

    public MediaProvider(Context context) {
        this.context = context;
    }

    public interface MediaProviderListener {
        void onResponse(Media[] arrayMedia);
        void onErrorResponse(VolleyError error);
    }

    public interface MediaDetailProviderListener {
        void onResponse(Media mediaItem);
        void onErrorResponse(VolleyError error);
    }

    public void getMedia(final MediaProviderListener listener) {
        GsonRequest gsonRequest = new GsonRequest<>(BuildConfig.API_URL_GET_MEDIA, Media[].class, null, new Response.Listener<Media[]>() {

            @Override
            public void onResponse(Media[] arrayMedia) {
                listener.onResponse(arrayMedia);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponse(error);
            }
        });

        RequestManager.getInstance().addToRequestQueue(context, gsonRequest);
    }

    public void getMediaItem(int id, final MediaDetailProviderListener listener) {
        GsonRequest gsonRequest = new GsonRequest<>(String.format(BuildConfig.API_URL_MEDIA_DETAIL, id), Media.class, null, new Response.Listener<Media>() {

            @Override
            public void onResponse(Media mediaItem) {
                listener.onResponse(mediaItem);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponse(error);
            }
        });

        RequestManager.getInstance().addToRequestQueue(context, gsonRequest);
    }

}
