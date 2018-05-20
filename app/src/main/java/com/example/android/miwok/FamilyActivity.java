package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends Activity {
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mediaPlayer.release();
        }
    };
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener(){
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
            mediaPlayer.start();
        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
            mediaPlayerRelease();
        }
    }
};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        final ArrayList<word> words = new ArrayList<word>();
        words.add(new word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new word("older brother", "taachi", R.drawable.family_older_brother,
                R.raw.family_older_brother));
        words.add(new word("younger brother", "chalitti", R.drawable.family_younger_brother,
                R.raw.family_younger_brother));
        words.add(new word("older sister", "teṭe", R.drawable.family_older_sister,
                R.raw.family_older_sister));
        words.add(new word("younger sister", "kolliti", R.drawable.family_younger_sister,
                R.raw.family_younger_sister));
        words.add(new word("grandmother ", "ama", R.drawable.family_grandmother,
                R.raw.family_grandmother));
        words.add(new word("grandfather", "paapa", R.drawable.family_grandfather,
                R.raw.family_grandfather));


        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);

        WordAdapter adapter = new WordAdapter(this,words,R.color.category_family);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mediaPlayerRelease();
                        word Word = words.get(position);
                        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                        int result = audioManager.requestAudioFocus(afChangeListener,
                                // Use the music stream.
                                audioManager.STREAM_MUSIC,
                                // Request permanent focus.
                                audioManager.AUDIOFOCUS_GAIN);

                        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                        mediaPlayer = MediaPlayer.create(FamilyActivity.this,Word.getAudioResourceId());
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(mCompletionListener);
                    }
                }    }

        );
    }
    private void mediaPlayerRelease(){
        if (mediaPlayer != null){

            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }
    @Override
    protected void onStop() {
        mediaPlayerRelease();
        super.onStop();
    }
}
