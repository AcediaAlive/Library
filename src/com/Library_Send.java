package com;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Library_Send {
    static class sendMessThread extends Thread {
        String messsgae = "";
        Socket socket = null;

        public sendMessThread(String messsgae, Socket socket) {
            this.messsgae = messsgae;
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            //写操作
            OutputStream os = null;
            try {
                os = socket.getOutputStream();
                String in = "";
                in = messsgae;
                os.write(("" + in).getBytes());
                os.flush();

            } catch (IOException e) {
                //e.printStackTrace();
//                System.out.println(socket+" 掉线");
//                Library_Server.remove(socket);
            }

        }
    }

//    static class testAlive extends Thread{
//        public void run(){
//            while (true){
//                try {
//                    for (int i = 0; i < Library_Server.client.size(); i++) {
//                        Mod_Send.sendmessage("alive?\n",Library_Server.client.get(i).getSocket());
//                    }
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//
//                }
//            }
//        }
//    }

    public static void sendmessage(String msg, Socket socket) {
        //new sendMessThread(msg,socket).start();
        Library_Server.executorService.execute(new sendMessThread(msg, socket));
    }
}
