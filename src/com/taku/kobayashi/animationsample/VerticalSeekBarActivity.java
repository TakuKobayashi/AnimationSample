package com.taku.kobayashi.animationsample;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class VerticalSeekBarActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.vertical_seekbar_view);
    final TextView tv = (TextView) findViewById(R.id.SeekBarText);
    VerticalSeekBar seekBar = (VerticalSeekBar) findViewById(R.id.VerticalSeekBar);
    seekBar.setMax(1000);
    tv.setText(String.valueOf(seekBar.getProgress()) + "/" + String.valueOf(seekBar.getMax()));
    seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
      
      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        
      }
      
      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        
      }
      
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tv.setText(String.valueOf(seekBar.getProgress()) + "/" + String.valueOf(seekBar.getMax()));
      }
    });
  }
}
