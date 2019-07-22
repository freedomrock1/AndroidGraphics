package edu.csc4360.graphics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class CustomView2Activity extends AppCompatActivity {
    CirclesView2 circlesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view2);
//        circlesView = findViewById(R.id.circlesView);
//        circlesView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // Display a random number of circles
//                Random random = new Random();
//                circlesView.setNumCircles(random.nextInt(20) + 1);
//            }
//        });
    }
}
