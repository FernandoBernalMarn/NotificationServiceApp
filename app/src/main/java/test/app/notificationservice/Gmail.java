package test.app.notificationservice;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;

/**
 * Created by Carolina on 10/11/2014.
 */
public class Gmail {

    Firebase ref;
    Firebase userChild;
    Firebase appChild;
    static final String TAG = "GmailClass";

    public void startAlertG(){
        Log.i(TAG,"startAlertG");
        Firebase.setAndroidContext(MyActivity.getAppContext());
        ref = new Firebase("https://flickering-inferno-1612.firebaseio.com/Users");
        userChild = ref.child("User1");
        appChild = userChild.child("Gmail");

        //appChild.setValue(123);

        appChild.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData data) {
                if (data.getValue() == null){
                    Log.i(TAG, "Gmail is null");
                    data.setValue(1);
                }
                return Transaction.success(data);
            }

            @Override
            public void onComplete(FirebaseError firebaseError, boolean b, DataSnapshot dataSnapshot) {

            }
        });
    }
}
