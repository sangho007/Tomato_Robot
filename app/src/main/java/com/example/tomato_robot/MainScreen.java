package com.example.tomato_robot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private ClientSocketThread clientSocketThread;
    AppVariable app_variable = new AppVariable();

    private ArrayList<HarvestHistory.HarvestItem> harvestItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Button btn_harvest_history = findViewById(R.id.btn_harvest_history);
        Button btn_intake_history = findViewById(R.id.btn_intake_history);
        Button btn_robot_setting = findViewById(R.id.btn_robot_setting);

        View act_state_rect = findViewById(R.id.act_state_rect);
        ImageView act_state_img = findViewById(R.id.act_state_img);
        TextView act_state_text = findViewById(R.id.act_state_text);

        AppVariable app = (AppVariable) getApplicationContext();
        harvestItems = app.getHarvestItemList();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Sleep for 3000 ms (3 seconds)
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Update your variable here after 3 seconds.
                        app_variable.act_state = ("operating");
                        act_state_rect.setBackground(getResources().getDrawable(R.drawable.act_enabled_background));
                        act_state_img.setImageDrawable(getResources().getDrawable(R.drawable.act_icon));
                        act_state_text.setText(app_variable.act_state);
                    }
                });
            }
        }).start();


//        clientSocketThread = new ClientSocketThread("172.30.1.31", 12345);
//        clientSocketThread = new ClientSocketThread("192.168.2.2", 12345);
        clientSocketThread = new ClientSocketThread("192.168.0.205", 12345); // 젯슨 AESLAB 공유기 내부주소
        clientSocketThread.start();

        act_state_rect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (app_variable.act_state.equals("waiting")){
                    app_variable.act_state = ("operating");
                    act_state_rect.setBackground(getResources().getDrawable(R.drawable.act_enabled_background));
                    act_state_img.setImageDrawable(getResources().getDrawable(R.drawable.act_icon));
                    act_state_text.setText(app_variable.act_state);
                    clientSocketThread.setNew_message("operating");
                }
                else if (app_variable.act_state.equals("operating")){
                    app_variable.act_state = ("waiting");
                    act_state_rect.setBackground(getResources().getDrawable(R.drawable.act_disabled_background));
                    act_state_img.setImageDrawable(getResources().getDrawable(R.drawable.sleep_icon));
                    act_state_text.setText(app_variable.act_state);
                    clientSocketThread.setNew_message("waiting");
                    app.harvestItems.add(new HarvestHistory.HarvestItem("2023-09-20","423"));
                    app.intakeItems.add(new IntakeHistory.IntakeItem("2023-09-20","423"));
                }
            }
        });

        btn_harvest_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this,HarvestHistory.class);

                startActivity(intent);
            }
        });

        btn_intake_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this,IntakeHistory.class);
                startActivity(intent);
            }
        });

        btn_robot_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this,RobotSetting.class);
                clientSocketThread.setNew_message("close");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // When activity is destroyed, interrupt the thread.
        if (clientSocketThread != null) {
            clientSocketThread.interrupt();
        }
    }
}