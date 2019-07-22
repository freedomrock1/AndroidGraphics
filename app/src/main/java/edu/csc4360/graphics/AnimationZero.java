package edu.csc4360.graphics;

import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimationZero extends AppCompatActivity {
    ImageView rocketImage;
    ImageView ivBison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_zero);

        rocketImage = findViewById(R.id.ivRocket);
        rocketImage.setBackgroundResource(R.drawable.flying_rocket);
        final AnimationDrawable rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
        rocketImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rocketAnimation.isRunning()) {
                    rocketAnimation.stop();
                }
                else {
                    rocketAnimation.start();
                }
            }
        });

        ivBison = findViewById(R.id.ivBison);
        bisonAnim = AnimationUtils.loadAnimation(this, R.anim.aninzero);

    }

    Animation bisonAnim;

    public void onClickBison(View view) {
        ivBison.startAnimation(bisonAnim);
    }


    private void setBack(){




    }
}
