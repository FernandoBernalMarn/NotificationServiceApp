package test.app.notificationservice;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Switch;

/**
 * Created on 09/12/2014.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotifListenerService extends NotificationListenerService {

    final String TAG ="NotifListenerService";
    String packageName;
    Facebook facebook;
    Gmail gmail;
    Whatsapp whatsapp;
    boolean isNotificationPosted;

    @Override
    public void onCreate(){
        Log.i(TAG,"onCreate");

        // Show notification when service is started.
        showNotification(true);
    }

    @Override
    public void onDestroy(){
        Log.i(TAG, "onDestroy");

        // Delete the notification when service is stopped.
        showNotification(false);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        packageName = statusBarNotification.getPackageName(); // Store the package name on the variable.
        Log.i(TAG,packageName);
        isNotificationPosted = true; // Assign value true as it is notification posted.
        notificationManager(packageName, isNotificationPosted); //
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        packageName = statusBarNotification.getPackageName(); // Store the package name on the variable.
        Log.i(TAG,packageName);
        isNotificationPosted = false; // Assign value false as it is notification removes (isn't notification posted).
        notificationManager(packageName,isNotificationPosted);
    }

    // Parameters for configuring the notification when the service is called.
    private void showNotification(boolean show){
        Resources r = getResources();

        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this,MyActivity.class), 0);

        // Set the parameters for notification showing.
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(r.getString(R.string.notification_description))
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(r.getString(R.string.notif_tittle_text))
                .setContentText(r.getString(R.string.notif_contento_text))
                .setContentIntent(pi)
                .setAutoCancel(false)
                .build();

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        // Show or delete notification when the service is created or deleted.
        if (show)
            notificationManager.notify(0, notification);
        else
            notificationManager.cancel(0);
    }

    // Method for call the class which manage the different resource for each app.
    private void notificationManager(String pkName, boolean isNotifPosted){
        switch(pkName){
            case "com.facebook.katana":
                facebook = new Facebook();
                facebook.alertFacebook(isNotifPosted);
                break;
            case "com.google.android.gm":
                gmail = new Gmail();
                gmail.alertGmail(isNotifPosted);
                break;
            case "com.whatsapp":
                whatsapp = new Whatsapp();
                whatsapp.alertWhatsapp(isNotifPosted);
                break;
        }
    }
}
