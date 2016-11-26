package com.example.yokaa.movieapp.Volley;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by yokaa on 11/17/16.
 */

public class VolleySingletone {

    private static VolleySingletone sInstance;
    private RequestQueue mRequestQueue;

    private VolleySingletone(){

        mRequestQueue= Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public static VolleySingletone getInstance(){
        if (sInstance==null)
            sInstance=new VolleySingletone();

        return sInstance ;
    }

    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }
}


