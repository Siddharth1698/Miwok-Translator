package com.example.android.miwok;

import android.media.MediaPlayer;
import android.view.View;

public class word {
    private String DefaultTranslation;
    private String MiwokTranslation;
    private int imageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int AudioResource;


    public word(String defaultTranslation, String miwokTranslation, int audioResource) {
        DefaultTranslation = defaultTranslation;
        MiwokTranslation = miwokTranslation;
        AudioResource = audioResource;
    }

    public word(String defaultTranslation, String miwokTranslation, int imgResourceId, int audioResource) {
        DefaultTranslation = defaultTranslation;
        MiwokTranslation = miwokTranslation;
        imageResourceId = imgResourceId;
        AudioResource = audioResource;

    }

    public String getDefaultTranslation() {
        return DefaultTranslation;
    }

    public int getImageResourceId(){
       return imageResourceId;
    }

    public String getMiwokTranslation() {
        return MiwokTranslation;
    }

    public boolean hasImage(){
        return imageResourceId != NO_IMAGE_PROVIDED;
    }


    public int getAudioResourceId() {
        return AudioResource;
    }
}
