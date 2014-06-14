package com.taku.kobayashi.animationsample;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
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

    Spinner spinner = (Spinner) findViewById(R.id.AnimationKindSpinner);
    spinner.setAdapter(adapter);
    spinner.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        
      }
    });

    ImageView imageView = (ImageView) findViewById(R.id.AnimationSampleImage);
    imageView.setImageResource(R.drawable.sample);
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
