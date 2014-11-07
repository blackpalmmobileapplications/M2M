package com.cjohnson.m2m;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    private Button stopButton = null;
    private Button playButton = null;
    private Button rewindButton = null;
    private Button skipButton = null;
    private Button pauseButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        playButton = (Button)findViewById(R.id.playbutton);
        playButton.setOnClickListener(this);



        stopButton = (Button)findViewById(R.id.stopbutton);
        stopButton.setOnClickListener(this);




        rewindButton = (Button)findViewById(R.id.rewindbutton);
        rewindButton.setOnClickListener(this);





        skipButton = (Button)findViewById(R.id.skipbutton);
        skipButton.setOnClickListener(this);




        pauseButton = (Button)findViewById(R.id.pausebutton);
        pauseButton.setOnClickListener(this);


    }

    public void onClick(View target) {
        // Send the correct intent to the MusicService, according to the button that was clicked
        if (target == playButton)
            startService(new Intent(MusicService.ACTION_PLAY));
        else if (target == pauseButton)
            startService(new Intent(MusicService.ACTION_PAUSE));
        else if (target == skipButton)
            startService(new Intent(MusicService.ACTION_SKIP));
        else if (target == rewindButton)
            startService(new Intent(MusicService.ACTION_REWIND));
        else if (target == stopButton)
            startService(new Intent(MusicService.ACTION_STOP));

        {
            Intent intent = new Intent(this, MusicService.class);
            stopService(intent);
        }
    }
}


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

} */
