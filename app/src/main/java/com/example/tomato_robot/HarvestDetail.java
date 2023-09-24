package com.example.tomato_robot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HarvestDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harvest_detail);

        TextView date = findViewById(R.id.detail_date);
        TextView category = findViewById(R.id.detail_category);

        TextView weight1 = findViewById(R.id.detail_weight1);
        TextView weight2 = findViewById(R.id.detail_weight2);
        TextView weight3 = findViewById(R.id.detail_weight3);

        Intent intent = getIntent();

        date.setText("날짜 : "+intent.getStringExtra("date"));
        category.setText("종류 : "+"토마토");
        weight1.setText("토마토 무게 : " + intent.getStringExtra("weight")+"g");
        weight2.setText("");
        weight3.setText("");

        Button btn_detail_back = findViewById(R.id.btn_detail_back);

        btn_detail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}