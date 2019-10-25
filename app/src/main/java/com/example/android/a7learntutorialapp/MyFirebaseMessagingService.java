/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.a7learntutorialapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import android.util.Log;

import com.example.android.a7learntutorialapp.view.activity.MainActivity;
import com.example.android.a7learntutorialapp.view.activity.PostActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
//import com.google.firebase.quickstart.fcm.R;
//
//import androidx.work.OneTimeWorkRequest;
//import androidx.work.WorkManager;

/**
 * NOTE: There can only be one service in each app that receives FCM messages. If multiple
 * are declared in the Manifest then the first one will be chosen.
 * <p>
 * In order to make this Java sample functional, you must remove the following from the Kotlin messaging
 * service in the AndroidManifest.xml:
 * <p>
 * <intent-filter>
 * <action android:name="com.google.firebase.MESSAGING_EVENT" />
 * </intent-filter>
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    private static final String ACTIVITY_MAIN = "activity_main";
    private static final String ACTIVITY_POST = "activity_post";

    private static final String DESTINATION_TYPE_URL = "url";
    private static final String DESTINATION_TYPE_ACTIVITY = "activity";

    private static final String DESTINATION_TYPE = "destination_type";
    private static final String DESTINATION = "destination";

    private static final String EXTRA_POST_TITLE = "post_title";
    private static final String EXTRA_POST_CONTENT = "post_content";
    private static final String EXTRA_POST_DATE = "post_date";
    private static final String EXTRA_POST_IMAGE_URL = "post_image_url";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages
        // are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data
        // messages are the type
        // traditionally used with GCM. Notification messages are only received here in
        // onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated
        // notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages
        // containing both notification
        // and data payloads are treated as notification messages. The Firebase console always
        // sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Map<String, String> data = remoteMessage.getData();
            switch (data.get(DESTINATION_TYPE)) {
                case DESTINATION_TYPE_ACTIVITY:
                    switch (data.get(DESTINATION)) {
                        case ACTIVITY_MAIN:
                            Intent showMainActivity = new Intent(this, MainActivity.class);
                            //ممکن است هنگام ارسال نوتیفیکیشن اپ در حال اجرا نباشد. در این مواقع از فلگ زیر روی اینتنت، جهت ایجاد تسک جدید برای اپ، استفاده میکنیم
                            showMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            sendNotification(remoteMessage.getNotification().getBody(), showMainActivity);
                            break;
                        case ACTIVITY_POST:
                            Intent showPostActivityIntent = new Intent(this, PostActivity.class);
                            String postTitle = data.get(EXTRA_POST_TITLE);
                            String postContent = data.get(EXTRA_POST_CONTENT);
                            String postImageUrl = data.get(EXTRA_POST_IMAGE_URL);
                            String postDate = data.get(EXTRA_POST_DATE);
                            showPostActivityIntent.putExtra(SevenLearnDatabaseOpenHelper.COL_TITLE, postTitle);
                            showPostActivityIntent.putExtra(SevenLearnDatabaseOpenHelper.COL_CONTENT, postContent);
                            showPostActivityIntent.putExtra(SevenLearnDatabaseOpenHelper.COL_POST_IMAGE_URL, postImageUrl);
                            showPostActivityIntent.putExtra(SevenLearnDatabaseOpenHelper.COL_DATE, postDate);
                            showPostActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            sendNotification(remoteMessage.getNotification().getBody(), showPostActivityIntent);
                    }
                    break;
                case DESTINATION_TYPE_URL:
                    String url = data.get(DESTINATION);
                    Intent showWebPageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    showWebPageIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    sendNotification(remoteMessage.getNotification().getBody(), Intent.createChooser(showWebPageIntent,
                            "انتخاب مرورگر..."));
                    break;
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]


    // [START on_new_token]

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }
    // [END on_new_token]

    /**
     * Schedule async work using WorkManager.
     */
//    private void scheduleJob() {
//        // [START dispatch_job]
//        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(MyWorker.class)
//                .build();
//        WorkManager.getInstance().beginWith(work).enqueue();
//        // [END dispatch_job]
//    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Persist token to third-party servers.
     * <p>
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody, Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_stat_notification)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentTitle("7Learn")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}