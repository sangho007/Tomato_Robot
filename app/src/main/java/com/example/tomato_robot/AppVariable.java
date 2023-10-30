package com.example.tomato_robot;

import android.app.Application;

import java.util.ArrayList;

public class AppVariable extends Application {
    String act_state = "waiting";


    boolean socketFlag = false;

    ClientSocketThread socketThread;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    private OnActStateChangeListener listener;

    public void setActState(String act_state) {
        this.act_state = act_state;
        if (listener != null) {
            listener.onActStateChange(act_state);
        }
    }

    public void setOnActStateChangeListener(OnActStateChangeListener listener) {
        this.listener = listener;
    }

    public interface OnActStateChangeListener {
        void onActStateChange(String act_state);
    }


    public ClientSocketThread getSocketThread() {
        return socketThread;
    }

    public void setSocketThread(ClientSocketThread socketThread) {
        this.socketThread = socketThread;
    }

    public void initSocketThread(String ip,int port){
        if (!socketFlag){
            this.socketThread = new ClientSocketThread(ip, port, this); // AppVariable 객체를 넘겨줍니다.
            this.socketThread.start();
            socketFlag = true;
        }
    }


    public void setNew_message(String message){
        this.socketThread.setNew_message(message);
    }
}
