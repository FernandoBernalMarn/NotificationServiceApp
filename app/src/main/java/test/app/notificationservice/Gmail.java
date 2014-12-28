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

    // Method for write in firebase.
    public void alertGmail(boolean isNotifPosted){
        Log.i(TAG,"startAlertG");
        ref = new Firebase("https://flickering-inferno-1612.firebaseio.com/Users"); // Main url in the firebase.
        userChild = ref.child("User2"); // Child url for the user.
        appChild = userChild.child("Gmail"); // Child url for app.

        if (isNotifPosted){
            // Transaction for add an pointer for the notification.
            appChild.runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData data) {
                    if (data.getValue() == null){
                        // The value is one if this is the first notification.
                        data.setValue(1);
                    }
                    return Transaction.success(data);
                }

                @Override
                public void onComplete(FirebaseError firebaseError, boolean b, DataSnapshot dataSnapshot) {

                }
            });
        }
        else{
            // Transaction for delete an pointer for the notification.
            appChild.runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData data) {
                    if (data.getValue() != null){
                        // Delete the pointer if exist one or more notifications.
                        data.setValue(0);
                    }

                    return Transaction.success(data);
                }

                @Override
                public void onComplete(FirebaseError firebaseError, boolean b, DataSnapshot dataSnapshot) {

                }
            });
        }
    }
}
