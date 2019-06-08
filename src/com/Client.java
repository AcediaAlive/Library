package com;

import java.net.Socket;

public class Client {
    private String id;
    private String serialnumber;
    private Socket socket;
    private String lastMSGtime;
    private boolean auth = false;

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getLastMSGtime() {
        return lastMSGtime;
    }

    public void setLastMSGtime(String lastMSGtime) {
        this.lastMSGtime = lastMSGtime;
    }

    public Client(String id, String serialnumber, Socket socket, String lastMSGtime, boolean auth) {
        this.id = id;
        this.serialnumber = serialnumber;
        this.socket = socket;
        this.lastMSGtime = lastMSGtime;
        this.auth = auth;
    }
}
