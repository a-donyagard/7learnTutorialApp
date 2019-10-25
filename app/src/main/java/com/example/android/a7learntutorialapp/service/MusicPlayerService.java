package com.example.android.a7learntutorialapp.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.view.activity.MusicPlayerActivity;

/**
 * Created by Saeed shahini on 11/11/2016.
 */
public class MusicPlayerService extends Service {
    private static final String ACTION_PLAY = "com.example.sshahini.myapplication.PLAY_MUSIC";
    private static final String ACTION_FORWARD = "com.example.sshahini.myapplication.FORWARD_MUSIC";
    private static final String ACTION_REWIND = "com.example.sshahini.myapplication.REWIND_MUSIC";

    private MediaPlayer mediaPlayer;
    private MusicPlayerBinder musicPlayerBinder = new MusicPlayerBinder();

    private NotificationManager mNotifyManager;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        setupMediaPlayer();
        return musicPlayerBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction() == null) {
            intent.setAction("");
        }
        switch (intent.getAction()) {
            case ACTION_PLAY:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
                break;
            case ACTION_FORWARD:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
                break;
            case ACTION_REWIND:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
                break;
            default:
                createNotificationChannel();

                Intent showMusicPlayerActivityIntent = new Intent(this, MusicPlayerActivity.class);
                showMusicPlayerActivityIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                Intent playIntent = new Intent(this, MusicPlayerService.class);
                playIntent.setAction(ACTION_PLAY);
                PendingIntent playPendingIntent = PendingIntent.getService(this, 0, playIntent, 0);

                Intent forwardIntent = new Intent(this, MusicPlayerService.class);
                forwardIntent.setAction(ACTION_FORWARD);
                PendingIntent forwardPendingIntent = PendingIntent.getService(this, 0, forwardIntent, 0);

                Intent rewindIntent = new Intent(this, MusicPlayerService.class);
                rewindIntent.setAction(ACTION_REWIND);
                PendingIntent rewindPendingIntent = PendingIntent.getService(this, 0, rewindIntent, 0);

                Notification notification = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_music_note_black_36dp)
                        .setContentTitle("7Learn Music Player")
                        .setContentText("Ebro Yashar.mp3")
                        .setOngoing(true)
                        .setContentIntent(PendingIntent.getActivity(this, 0, showMusicPlayerActivityIntent, 0))
                        .addAction(R.drawable.ic_fast_rewind_black_48dp, "Rewind", rewindPendingIntent)
                        .addAction(R.drawable.ic_action_play_black, "play", playPendingIntent)
                        .addAction(R.drawable.ic_fast_forward_black_48dp, "Forward", forwardPendingIntent)
                        .build();
                startForeground(101, notification);
                break;
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.release();
        super.onDestroy();
    }

    private void setupMediaPlayer() {
        /*
        File musicDirectory = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File music = new File(musicDirectory, "Ebru-yasar-seni-anan-benim-icin-dogurmus.mp3");
        if (music.exists()) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(this, Uri.fromFile(music));
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {

                    }
                });

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        stopForeground(true);
                        stopSelf();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Music not found", Toast.LENGTH_LONG).show();
        }
        */
        mediaPlayer = MediaPlayer.create(this, R.raw.invite_kakoband);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopForeground(true);
                stopSelf();
            }
        });
    }


    public void createNotificationChannel() {
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Music Player Notification", NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from MusicPlayer");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }


    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public class MusicPlayerBinder extends Binder {
        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }
}
