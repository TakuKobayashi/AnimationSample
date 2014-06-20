package com.taku.kobayashi.animationsample;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class TextAnimationActivity extends Activity {
  
  private RelativeLayout _layer;
  private int _viewId = 0;
  private int _topViewId = 0;
  private int _counter = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.text_animation_view);

    _layer = (RelativeLayout) findViewById(R.id.TextLayerAnimation);

    String[] textAnimetionList = this.getResources().getStringArray(R.array.text_animation_kind_list);
    ArrayAdapter<String> textAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
    textAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    for(int i = 0;i < textAnimetionList.length;++i){
      textAdapter.add(textAnimetionList[i]);
    }
    final Spinner textSpinner = (Spinner) findViewById(R.id.TextAnimationKindSpinner);
    textSpinner.setAdapter(textAdapter);

    final EditText tweetText = (EditText) findViewById(R.id.EditTextAnimation);
    Button textAnimationButton = (Button) findViewById(R.id.TextAnimationStartButton);
    textAnimationButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        int position = textSpinner.getSelectedItemPosition();
        final TextView text = new TextView(TextAnimationActivity.this);
        _viewId++;
        if(position == 0){
          String string = tweetText.getEditableText().toString();
          DisplayMetrics displayMetrics = new DisplayMetrics();
          ((WindowManager) TextAnimationActivity.this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
          Animation animation = new TranslateAnimation(displayMetrics.widthPixels, -displayMetrics.widthPixels, 0, 0);
          animation.setDuration(5000);
          animation.setInterpolator(new LinearInterpolator());
          animation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
              
            }
            
            @Override
            public void onAnimationRepeat(Animation animation) {
              
            }
            
            @Override
            public void onAnimationEnd(Animation animation) {
              text.setVisibility(View.INVISIBLE);
            }
          });
          text.setAnimation(animation);
          text.setId(_viewId);
          Log.d("sample", "den:"+displayMetrics.scaledDensity + " d:"+displayMetrics.density);
          text.setTextSize(20f);
          text.setText(string);
          View topView = _layer.getChildAt(0);
          RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
          if(topView == null || topView.getVisibility() == View.INVISIBLE){
            for(int i = 1;i < _layer.getChildCount();++i){
              View child = _layer.getChildAt(i);
              if(child.getVisibility() == View.INVISIBLE){
                _layer.removeView(child);
              }
            }
            rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            _layer.addView(text, 0, rlp);
          }else{
            rlp.addRule(RelativeLayout.BELOW, _viewId - 1);
            _layer.addView(text, rlp);
          }
        }else if(position == 1){
          Handler.Callback callback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
              Bundle params = (Bundle) msg.obj;
              int counter = params.getInt("counter");
              counter++;
              String word = params.getString("text");
              if(counter <= word.length()){
                text.setText(word.substring(0, counter));
                params.putInt("counter", counter);
                Message newMsg = new Message();
                newMsg.obj = params;
                Handler target = msg.getTarget();
                target.sendMessageDelayed(newMsg, 200);
              }
              return false;
            }
          };
          Handler handler = new Handler(callback);
          Message msg = new Message();
          Bundle bundle = new Bundle();
          bundle.putString("text", tweetText.getEditableText().toString());
          bundle.putInt("counter", 0);
          msg.obj = bundle;
          handler.sendMessage(msg);
          text.setId(_viewId);
          _layer.addView(text);
        }
      }
    });
  }
}
