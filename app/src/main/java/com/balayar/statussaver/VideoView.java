package com.balayar.statussaver;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class VideoView extends AppCompatActivity {

    MediaController mediaControls;
    android.widget.VideoView videoView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        videoView = findViewById(R.id.video_view);
        Intent intent = getIntent();
        String file1 = intent.getStringExtra("file");
        File file = new File(file1);


        if (mediaControls == null) {
            mediaControls = new MediaController(VideoView.this);
            mediaControls.setAnchorView(videoView);
        }
        videoView.setMediaController(mediaControls);
        videoView.setVideoURI(Uri.parse(String.valueOf(file)));

        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Thank You...!!!", Toast.LENGTH_LONG).show(); // display a toast when an video is completed
            }
        });

        videoView.setOnErrorListener((mp, what, extra) -> {
            Toast.makeText(getApplicationContext(), "Oops An Error Occur While Playing Video...!!!", Toast.LENGTH_LONG).show();
            return false;
        });

    }
}
