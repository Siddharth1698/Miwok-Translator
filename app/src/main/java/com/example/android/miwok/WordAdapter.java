package com.example.android.miwok;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<word> {

    TextView miwokTextView;
    TextView defaultTextView;
    private int colorResourceId;


    public WordAdapter(NumbersActivity numbersActivity, ArrayList<word> words, int category_numbers) {
        super(numbersActivity, 0, words);
        colorResourceId = category_numbers;


    }
    public WordAdapter(PhrasesActivity phrasesActivity, ArrayList<word> words, int category_phrases) {
        super(phrasesActivity, 0, words);
        colorResourceId = category_phrases;

    }

    public WordAdapter(ColorsActivity colorsActivity, ArrayList<word> words, int category_colors) {
        super(colorsActivity, 0, words);
        colorResourceId = category_colors;

    }
    public WordAdapter(FamilyActivity familyActivity, ArrayList<word> words, int category_family) {
        super(familyActivity, 0, words);
        colorResourceId = category_family;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        word currentWord = getItem(position);
        miwokTextView = (TextView) listItemView.findViewById(R.id.miwokTextView);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        defaultTextView = (TextView) listItemView.findViewById(R.id.defaultTextView);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView);

        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        View texter = listItemView.findViewById(R.id.texter);
        int color = ContextCompat.getColor(getContext(),colorResourceId);
        texter.setBackgroundColor(color);



        return listItemView;
    }
}