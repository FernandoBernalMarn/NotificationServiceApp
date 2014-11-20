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
public class Whatsapp {

    Firebase ref;
    Firebase userChild;
    Firebase appChild;
    String TAG =  "WhatsappClass";

    public void startAlertW(){
        Log.i(TAG,"startAlertW");
        Firebase.setAndroidContext(MyActivity.getAppContext());
        ref = new Firebase("https://flickering-inferno-1612.firebaseio.com/Users");
        userChild = ref.child("User1");
        appChild = userChild.child("Whatsapp");

        //appChild.setValue(132);

        appChild.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData data) {
                if (data.getValue() == null){
                    Log.i(TAG,"Whatsapp in null");
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
