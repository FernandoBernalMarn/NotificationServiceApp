package test.app.notificationservice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.firebase.client.Firebase;

public class MyActivity extends Activity{

    //private static Context context;
    static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Log.i(TAG,"onCreate");
        Firebase.setAndroidContext(this); // Send the context for firebase.
        //MyActivity.context = getAppContext();

        Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent);
    }

    /*public static Context getAppContext() {
        return MyActivity.context;
    }*/
}
