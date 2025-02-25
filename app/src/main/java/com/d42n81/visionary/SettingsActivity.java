package com.d42n81.visionary;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import yuku.ambilwarna.AmbilWarnaDialog;

public class SettingsActivity extends AppCompatActivity {

    //This activity allows the user to change preferences.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    //this method contains two color picker dialogs, text and BG
    public void openColorPicker(View v){
        SharedPreferences settings = getSharedPreferences("settings", 0);
        final String type = v.getResources().getResourceEntryName(v.getId());
        if (type.equals("fontColor")){
            AmbilWarnaDialog textColorPicker = new AmbilWarnaDialog(this, settings.getInt("fontColor", Color.WHITE), new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onCancel(AmbilWarnaDialog dialog) {
                }
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    saveSettings(type, color);
                }});
            textColorPicker.show();
        } else {
            AmbilWarnaDialog bgColorPicker = new AmbilWarnaDialog(this, settings.getInt("bgColor", Color.BLACK), new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onCancel(AmbilWarnaDialog dialog) {
                }
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    saveSettings(type, color);
                }});
            bgColorPicker.show();
        }
    }

    //Saves the users settings to a file called "settings" that is accessed my the reader activity
    public void saveSettings(String type, int color){
        SharedPreferences settings = getSharedPreferences("settings", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(type, color);
        editor.apply();
        Log.v("buttonname", type);
    }
}
