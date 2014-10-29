package test.app.notificationservice;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;


/**
 * Created by Carolina on 16/10/2014.
 */
public class UserAlert {

    Firebase ref;
    Firebase userChild;
    Firebase appChild;
    String appCh;

    public void startAlert(String packageName){
        Firebase.setAndroidContext(MyActivity.getAppContext());
        ref = new Firebase("https://flickering-inferno-1612.firebaseio.com/Users");
        userChild = ref.child("User1");
        appCh = notificationManager(packageName);
        appChild = userChild.child(appCh);

        if (appCh.equals("Facebook")){
            appChild.runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData mutableData) {
                    if(mutableData.getValue() == null){
                        mutableData.setValue(1);
                    }
                    else{
                        mutableData.setValue((Long)mutableData.getValue()+1);
                    }

                    return Transaction.success(mutableData);
                }

                @Override
                public void onComplete(FirebaseError firebaseError, boolean b, DataSnapshot dataSnapshot) {

                }
            });
        }
        else{
            appChild.runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData mutableData) {
                    if(mutableData.getValue() == null){
                        mutableData.setValue(1);
                    }
                    return Transaction.success(mutableData);
                }

                @Override
                public void onComplete(FirebaseError firebaseError, boolean b, DataSnapshot dataSnapshot) {

                }
            });
        }
    }

    // Method to initiate alert
    public String notificationManager(String packageName){
        String childApp="";

        switch (packageName){
            case "com.facebook.katana":
                childApp = "Facebook";
                break;
            case "com.google.android.gm":
                childApp = "Gmail";
                break;
            case "com.whatsapp":
                childApp = "Whatsapp";
        }
        return childApp;
    }

    public void stopAlert(){

    }

    public void deleteAllAlerts(){

    }
}
