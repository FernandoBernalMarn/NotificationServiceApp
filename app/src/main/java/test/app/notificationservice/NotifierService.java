package test.app.notificationservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by Carolina on 03/10/2014.
 */
public class NotifierService extends AccessibilityService {

    static final String TAG = "notification-Service";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i(TAG, "onAccessibilityEvent");
        CharSequence packageName = event.getPackageName();

        // Identify the event and package for the notifications incoming.
        switch (event.getEventType()){
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                // Cancel alert.
                notificationManager(packageName, false);
                break;
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                // Initiate alert.
                notificationManager(packageName, true);
        }

    }

    @Override
    public void onInterrupt() {
        Log.i(TAG,"onInterrupt");
    }

    @Override
    protected void onServiceConnected() {
        Log.i(TAG,"onServiceConnected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED + AccessibilityEvent.TYPE_VIEW_FOCUSED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        info.notificationTimeout = 100;
        setServiceInfo(info);

        // Show notification when service is started
        showNotification(true);
    }

    @Override
    public void onDestroy(){
        Log.i(TAG,"omDestroy");

        // Delete the notification when service is stopped
        showNotification(false);
    }

    private void showNotification(boolean show){
        Resources r = getResources();

        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this,MyActivity.class), 0);

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

        if (show)
            notificationManager.notify(0, notification);
        else
            notificationManager.cancel(0);
    }


    // Method for management notification
    private void notificationManager(CharSequence pachageName, boolean incomingNotification){
        // Code pending
    }
}
