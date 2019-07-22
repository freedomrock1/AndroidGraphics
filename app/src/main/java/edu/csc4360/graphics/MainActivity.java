package edu.csc4360.graphics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Button btnHello, btnAnimationZero, btnCustomView, btnDotty, btnPropertyAnimation;
    TextView tvCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnHello=findViewById(R.id.btnHello);
//        btnAnimationZero=findViewById(R.id.btnAniZero);
//        btnCustomView=findViewById(R.id.btnCustomView);
//        btnDotty=findViewById(R.id.);
//        btnPropertyAnimation=findViewById(R.id.btnHello);
        tvCurrent=findViewById(R.id.tvCurrentTag);
    }

    public void onClickButton(View view){
        String tag=getResources().getResourceEntryName(  view.getId());
        Intent intent=new Intent(MainActivity.this, HelloActivity.class);
        tvCurrent.setText(tag);
        switch (tag){
            case "btnHello":
                intent = new Intent(MainActivity.this, HelloActivity.class);
                break;
            case "btnAniZero":
                intent = new Intent(MainActivity.this, AnimationZero.class);
                break;
            case "btnPropertyAnimation":
                intent = new Intent(MainActivity.this, PropertyAnimationActivity.class);
                break;
            case "btnCustomView":
                intent = new Intent(MainActivity.this, CustomViewActivity.class);
                break;
            case "btnDotty":
                intent = new Intent(MainActivity.this, DottyAppActivity.class);
                break;

            case "btnCustomView2":
                intent = new Intent(MainActivity.this, CustomView2Activity.class);
                break;

        }
        startActivity(intent);
    }
}
