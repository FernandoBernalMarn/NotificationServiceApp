package test.app.notificationservice;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class MyActivity extends Activity{

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        MyActivity.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyActivity.context;
    }
}
