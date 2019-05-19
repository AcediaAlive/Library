package com;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Library_Receive extends Thread{
    public Socket socket;
    public String s;
    public  boolean isclose=false;
    @Override
    public void run() {
        while (!isclose) {
            try {
                InputStream in = socket.getInputStream();
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = in.read(buf)) != -1) {
                    s= new String(buf, 0, len);
                    System.out.println(s);
                    Controller c=new Controller();
                    c.function(socket,s);
                }
            } catch (IOException e) {
                isclose=true;
                System.out.println("Connection terminated.");
                //e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


    public Library_Receive(Socket socket) {
        this.socket = socket;
    }
}
