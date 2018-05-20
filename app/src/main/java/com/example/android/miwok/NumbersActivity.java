package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends Activity{

    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mediaPlayer.release();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        final ArrayList<word> words = new ArrayList<word>();
        words.add(new word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));


        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);

        WordAdapter adapter = new WordAdapter(this,words,R.color.category_numbers);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mediaPlayerRelease();
                        word Word = words.get(position);
                        mediaPlayer = MediaPlayer.create(NumbersActivity.this,Word.getAudioResourceId());
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(mCompletionListener);

                    }
                }
        );
}
       private void mediaPlayerRelease(){
        if (mediaPlayer != null){

            mediaPlayer.release();
            mediaPlayer = null;
        }
}

    @Override
    protected void onStop() {
        mediaPlayerRelease();
        super.onStop();
    }
}


