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
import android.widget.Spinner;

public class TextAnimationActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.text_animation_view);

    String[] textAnimetionList = this.getResources().getStringArray(R.array.text_animation_kind_list);
    ArrayAdapter<String> textAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
    textAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    for(int i = 0;i < textAnimetionList.length;++i){
      textAdapter.add(textAnimetionList[i]);
    }
    Spinner textSpinner = (Spinner) findViewById(R.id.TextAnimationKindSpinner);
    textSpinner.setAdapter(textAdapter);
    textSpinner.getSelectedItemPosition();

    EditText tweetText = (EditText) findViewById(R.id.EditTextAnimation);
    Button textAnimationButton = (Button) findViewById(R.id.TextAnimationStartButton);
  }
}
