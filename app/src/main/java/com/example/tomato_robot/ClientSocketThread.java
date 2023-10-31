package com.example.tomato_robot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientSocketThread extends Thread {
    private String host;
    private int port;
    private String old_message;
    private String new_message;

    // Socket과 입출력을 위한 객체들을 클래스 변수로 선언합니다.
    private Socket socket = null;
    private BufferedReader input = null;
    private PrintWriter output = null;
    private AppVariable appVariable;




    public ClientSocketThread(String host, int port, AppVariable appVariable) {
        this.host = host;
        this.port = port;
        this.old_message = "";  // Default message
        this.new_message = "";
        this.appVariable = appVariable; // 생성자에서 AppVariable 객체를 받아옵니다.
    }

    // Call this method to set the message you want to send.
    public void setOld_message(String old_message) {
        this.old_message = old_message;
    }

    public String getOld_message() {
        return old_message;
    }

    public String getNew_message() {
        return new_message;
    }

    public void setNew_message(String new_message) {
        this.new_message = new_message;
    }
    // Logger instance 생성


    private class ReceiverThread extends Thread {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    String receivedData = input.readLine();

                    if (receivedData != null) {
                        System.out.println("Received data: " + receivedData);
                        if (receivedData.equals("start")){
                            appVariable.setNew_message("operating");
                            appVariable.setActState("operating");
                        }

                    } else {
                        System.out.println("No data received.");
                        socket.close();
                        socket = new Socket(host, port);
                        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            socket = new Socket(host, port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), false);

            ReceiverThread receiverThread = new ReceiverThread();
            receiverThread.start();

            while (!Thread.currentThread().isInterrupted()) {

                if(output!=null ){
                    if (!old_message.equals(new_message)){
                        output.println(new_message);
                        old_message = new_message;
                    }

                    output.flush();
                }

                Thread.sleep(100); // Sleep for 100 milliseconds.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
