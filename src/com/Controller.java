package com;

import java.net.Socket;

public class Controller {
    public int function(Socket socket,String s){
        for(int i=0;i<ClientInfo.clientList.size();i++){
            if(ClientInfo.clientList.get(i).getSocket().equals(socket)){
                if(ClientInfo.clientList.get(i).isAuth()){
                    //已经登陆情况下  TODO:可执行命令
                    //下方为暂替代码
                    String[] str={String.valueOf(JsonParse.toMap(s).get("serialNumber"))};
                    return Msgcode.execute(1000,str);
                }else{
                    if(JsonParse.toMap(s).get("order").equals(1000)){
                        String[] str={String.valueOf(JsonParse.toMap(s).get("serialNumber"))};
                        return Msgcode.execute(1000,str);
                    }else if(JsonParse.toMap(s).get("order").equals(1100)){
                        String[] str={String.valueOf(JsonParse.toMap(s).get("id")),String.valueOf(JsonParse.toMap(s).get("serialNumber"))};
                        return Msgcode.execute(1100,str);
                    }else{
                        return -1;
                    }
                }
            }else{
                return -1;
            }
        }
        return -1;
    }
}
