package com;

import java.util.ArrayList;

public class ClientInfo {
    static ArrayList<Client> clientList=new ArrayList<Client>();
    static ArrayList<String> banList=new ArrayList<String>();
    public class testAlive extends Thread{
        public void run(){
            while (true){
                try {
                    banList.clear();
                    for (int i = 0; i < clientList.size(); i++) {
                        String[] s={clientList.get(i).getId()};
                        if(Database.query(Sql.getBan,s).size()>0){
                        banList.add(clientList.get(i).getId());
                        }
                    }
                    //System.out.println(banList);
                    Thread.sleep(60000);
                } catch (InterruptedException e) {

                }
            }
        }
    }
    public void run(){
        testAlive t=new testAlive();
        new Thread(t).start();
    }
}
