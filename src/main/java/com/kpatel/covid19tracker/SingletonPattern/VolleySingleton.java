package com.kpatel.covid19tracker.SingletonPattern;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton volleySingletonInstance;
    private RequestQueue requestQueueInstance;
//    private static Context ctx;

    private VolleySingleton(Context context) {
        requestQueueInstance = Volley.newRequestQueue(context.getApplicationContext());
        requestQueueInstance = getRequestQueue();
        //1. Context : is activity level context.
        //2. getApplicationContext :is context for application level
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (volleySingletonInstance == null) {
            volleySingletonInstance = new VolleySingleton(context);
        }
        return volleySingletonInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueueInstance;
    }
}
