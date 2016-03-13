package com.dirk41.mat;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private String mMyPackageName;
    private String mDefaultSmsAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGetDefaultSMSAppPriority = (Button) this.findViewById(R.id.get_default_sms_app_priority);
        Button buttonReleaseDefaultSMSAppPriority = (Button) this.findViewById(R.id.release_default_sms_app_priority);

//        buttonGetDefaultSMSAppPriority.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!mMyPackageName.equals(mDefaultSmsAppName)) {
//                    Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
//                    intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, mMyPackageName);
//                    MainActivity.this.startActivityForResult(intent, 0);
//                }
//            }
//        });
//
//        buttonReleaseDefaultSMSAppPriority.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mMyPackageName.equals(Telephony.Sms.getDefaultSmsPackage(MainActivity.this))) {
//                    Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
//                    intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, mDefaultSmsAppName);
//                    MainActivity.this.startActivity(intent);
//                }
//            }
//        });
//
//        mMyPackageName = this.getPackageName();
//        mDefaultSmsAppName = Telephony.Sms.getDefaultSmsPackage(this);

        Intent intentStartMATService = new Intent(this, MATService.class);
        this.startService(intentStartMATService);
    }
}
