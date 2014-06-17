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

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    String[] animationList = this.getResources().getStringArray(R.array.animation_kind_list);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    for(int i = 0;i < animationList.length;++i){
      adapter.add(animationList[i]);
    }

    final ImageView imageView = (ImageView) findViewById(R.id.AnimationSampleImage);
    imageView.setImageResource(R.drawable.sample);

    Spinner spinner = (Spinner) findViewById(R.id.AnimationKindSpinner);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Animation animation = null;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) MainActivity.this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        switch (position) {
        case 0:
          animation = new AlphaAnimation(0.2f, 0.8f);
          break;
        case 1:
          //アニメーションの物体を起点とした中心の座標(第5,6引数)を中心にfromからto度へ回転する
          animation = new RotateAnimation(0, 360, imageView.getWidth() >> 1, imageView.getHeight() >> 1);
          break;
        case 2:
          //アニメーションの物体を起点とした中心の座標(第5,6引数)を中心にfromからto倍へ拡大縮小する
          animation = new ScaleAnimation(0.5f, 2.0f, 0.5f, 2.0f, imageView.getWidth() >> 1, imageView.getHeight() >> 1);
          break;
        case 3:
          //元いた位置からX方向へfromからtoへ(ピクセル動く)
          animation = new TranslateAnimation((displayMetrics.widthPixels >> 1) + (imageView.getWidth() >> 1), (-displayMetrics.widthPixels >> 1) + (-imageView.getWidth() >> 1), 0, 0);
          break;
        default:
          break;
        }
        animation.setDuration(5000);
        animation.setRepeatCount(Animation.INFINITE);
        //繰り返しだけど反転させるアニメーション
        animation.setRepeatMode( Animation.REVERSE );
        imageView.setAnimation(animation);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        
      }
    });

    String[] textAnimetionList = this.getResources().getStringArray(R.array.text_animation_kind_list);
    ArrayAdapter<String> textAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
    textAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    for(int i = 0;i < textAnimetionList.length;++i){
      textAdapter.add(textAnimetionList[i]);
    }
    Spinner textSpinner = (Spinner) findViewById(R.id.TextAnimationKindSpinner);
    textSpinner.setAdapter(textAdapter);

    EditText tweetText = (EditText) findViewById(R.id.EditTextAnimation);
    Button textAnimationButton = (Button) findViewById(R.id.TextAnimationStartButton);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ImageView imageView = (ImageView) findViewById(R.id.AnimationSampleImage);
    BitmapDrawable bitmapDrawable = (BitmapDrawable)(imageView.getDrawable());
    if (bitmapDrawable != null) {
      bitmapDrawable.setCallback(null);
    }
    imageView.setImageBitmap(null);
  }
}
