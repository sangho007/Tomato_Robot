package com.example.tomato_robot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocketThread extends Thread {
    private String host;
    private int port;
    private String message;

    // Socket과 입출력을 위한 객체들을 클래스 변수로 선언합니다.
    private Socket socket = null;
    private BufferedReader input = null;
    private PrintWriter output = null;

    public ClientSocketThread(String host, int port) {
        this.host = host;
        this.port = port;
        this.message = "";  // Default message
    }

    // Call this method to set the message you want to send.
    public void setMessage(String message) {
        this.message = message;
    }




    @Override
    public void run() {
        try {
            socket = new Socket(host, port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            while (!Thread.currentThread().isInterrupted()) {

                if ((output != null) && !message.equals("")) {  // 출력 스트림이 유효할 때만 전송
                    output.println(message);
                    output.flush();
                    message = "";
                }

                // ACK 대기 및 수신
                String receivedData = input.readLine();

                if (receivedData != null) {
                    System.out.println("Received data: " + receivedData);
                } else {
                    System.out.println("No data received. Reconnecting...");
                    socket.close();
                    socket = new Socket(host, port);
                    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    output = new PrintWriter(socket.getOutputStream(), true);
                }
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
