package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Library_Server {
    private int port = 1234;
    private ServerSocket serverSocket;
    static ExecutorService executorService;

    public Library_Server() {
        try {
            serverSocket = new ServerSocket(port);
            executorService = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                System.out.println("wait client connect...");
                socket = serverSocket.accept();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        Library_Server sock = new Library_Server();
        sock.service();
    }

}
