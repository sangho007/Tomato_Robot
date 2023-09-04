package com.example.tomato_robot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ClientSocketThread clientSocketThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_signin = findViewById(R.id.btn_signin);
        Button btn_signup = findViewById(R.id.btn_signup);

//      ClientSocketThread clientSocketThread =  new ClientSocketThread("10.0.2.2",12345);
//        clientSocketThread =  new ClientSocketThread("172.30.1.31",12345);

        // Start the thread once here
//        clientSocketThread.start();

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainScreen.class);
//                clientSocketThread.setNew_message("operating");

                // Set the message to send and let the thread handle it in its own time.

                startActivity(intent);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);

                // Set the message to send and let the thread handle it in its own time.

//                clientSocketThread.setNew_message("waiting");
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
