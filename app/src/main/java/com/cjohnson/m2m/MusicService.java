package com.cjohnson.m2m;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;



public class MusicService extends Service {

    private MediaPlayer mediaPlayer = null;
    private boolean      isPlaying = false;

    private static int classID = 579; // just a number

    public static String START_PLAY = "START_PLAY";

    public static final String ACTION_TOGGLE_PLAYBACK =
            "com.cjohnson.m2m.action.TOGGLE_PLAYBACK";
    public static final String ACTION_PLAY = "com.cjohnson.m2m.action.PLAY";
    public static final String ACTION_PAUSE = "com.cjohnson.m2m.action.PAUSE";
    public static final String ACTION_STOP = "com.cjohnson.m2m.action.STOP";
    public static final String ACTION_SKIP = "com.cjohnson.m2m.action.SKIP";
    public static final String ACTION_REWIND = "com.cjohnson.m2m.action.REWIND";
    public static final String ACTION_URL = "com.cjohnson.m2m.action.URL";


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getBooleanExtra(START_PLAY, false)) {
            play();
        }
        return Service.START_STICKY;
    }

    private void play() {
        if (!isPlaying) {
            isPlaying = true;

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);

            PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

            Notification notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle("My Music Player")
                    .setContentText("Now Playing: \"sunset\"")
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentIntent(pi)
                    .build();

            mediaPlayer = MediaPlayer.create(this, R.raw.sunset);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();

            startForeground(classID, notification);
        }
    }

    @Override
    public void onDestroy() {
        stop();
    }

    private void stop() {
        if (isPlaying) {
            isPlaying = false;
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
            stopForeground(true);
        }
    }

}
