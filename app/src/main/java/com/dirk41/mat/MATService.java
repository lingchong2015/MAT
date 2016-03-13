package com.dirk41.mat;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by lingchong on 16-1-11.
 */
public class MATService extends Service {
    public static final String TAG = MATService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        SMSContent smsContent = new SMSContent(new Handler());
        this.getContentResolver().registerContentObserver(Uri.parse("content://sms"), true, smsContent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Intent intentStartMATService = new Intent(this, MATService.class);
//        this.startService(intentStartMATService);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class SMSContent extends ContentObserver {

        public SMSContent(Handler handler) {
            super(handler);
        }

        @Override
        public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

            try {
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(Uri.parse("content://sms"), null, null, null, null);
                if ((cursor.getCount() < 0) || (!cursor.moveToFirst())) {
                    return;
                }
                int typeIndex = cursor.getColumnIndex("type");
                int typeValue = cursor.getInt(typeIndex);
                if (typeValue == 6) {
                    int idIndex = cursor.getColumnIndex("_id");
                    String currentUri = "content://sms/" + cursor.getString(idIndex);
                    contentResolver.delete(Uri.parse(currentUri), null, null);
                    Log.i(TAG, String.format("Current URI: %s", currentUri));
                }
            } catch (Exception ex) {
                Log.i(TAG, ex.toString());
            }
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);

            ContentResolver resolver = getContentResolver();
            Cursor cursor = resolver.query(uri, null, null, null, null);
            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                String type = cursor.getString(9);
                if (type.equals("6")) {//2->send
                    resolver.delete(uri, null, null);
                }
            }
//            resolver.delete(uri, null, null);
        }
    }
}
