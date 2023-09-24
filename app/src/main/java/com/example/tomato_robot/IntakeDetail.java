package com.example.tomato_robot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class IntakeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intake_detail);

        TextView date = findViewById(R.id.detail_intake_date);
        TextView weight = findViewById(R.id.detail_intake_total_weight);
        ImageView img = findViewById(R.id.detail_intake_image);

        Intent intent = getIntent();
        date.setText("날짜 : "+ intent.getStringExtra("date"));



        if(Integer.parseInt(intent.getStringExtra("weight")) >= 400){
            img.setImageDrawable(getResources().getDrawable(R.drawable.happy_icon));
        } else if (Integer.parseInt(intent.getStringExtra("weight")) >= 300) {
            img.setImageDrawable(getResources().getDrawable(R.drawable.unhappy_icon));
        }else{
            img.setImageDrawable(getResources().getDrawable(R.drawable.angry_icon));
        }

        weight.setText("섭취량 : " + intent.getStringExtra("weight") + "g");

        Button btn = findViewById(R.id.btn_intake_detail_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}