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

    // Method for write in firebase.
    public void alertFacebook(boolean isNotifPosted){
        Log.i(TAG,"startAlertF");
        ref = new Firebase("https://...com/Users"); // Main url in the firebase.
        userChild = ref.child("User2"); // Child url for the user.
        appChild = userChild.child("Facebook"); // Child url for app.

        // Validate if is notification posted
        if (isNotifPosted){
            // Transaction for add an pointer for the notification.
            appChild.runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData data) {
                    if (data.getValue() == null){
                        // The value is one if this is the first notification.
                        Log.i(TAG,"onPost");
                        data.setValue(1);
                    }
                    else{
                        // If the valué is different from null get the valué and add plus one.
                        data.setValue((Long)data.getValue()+1);
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
                        Log.i(TAG,"onDelete");
                        data.setValue((Long)data.getValue()-1);
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
