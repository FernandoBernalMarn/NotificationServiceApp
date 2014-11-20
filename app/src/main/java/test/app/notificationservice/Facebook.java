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
public class Facebook {

    Firebase ref;
    Firebase userChild;
    Firebase appChild;
    String TAG = "Facebook";

    public void startAlertF(){
        Log.i(TAG,"startAlertF");
        Firebase.setAndroidContext(MyActivity.getAppContext());
        ref = new Firebase("https://flickering-inferno-1612.firebaseio.com/Users");
        userChild = ref.child("User1");
        appChild = userChild.child("Facebook");

        //appChild.setValue(321);

        appChild.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData data) {
                if (data.getValue() == null){
                    data.setValue(1);
                }
                else{
                    data.setValue((Long)data.getValue()+1);
                }

                return Transaction.success(data);
            }

            @Override
            public void onComplete(FirebaseError firebaseError, boolean b, DataSnapshot dataSnapshot) {

            }
        });
    }
}
