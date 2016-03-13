package com.dirk41.mat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by lingchong on 16-1-11.
 */
public class MATReceiver extends BroadcastReceiver {
//    public static final String TAG = "MATReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
//        Log.d(MATService.TAG, TAG + "onReceive()...");

        Intent intentStartMATService = new Intent(context, MATService.class);
        context.startService(intentStartMATService);
    }
}
