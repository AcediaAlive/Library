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
    ClientInfo ci=new ClientInfo();

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
                ci.clientList.add(new Client("","",socket,String.valueOf(System.currentTimeMillis()/1000),false));
                executorService.execute(new Library_Receive(socket));
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
