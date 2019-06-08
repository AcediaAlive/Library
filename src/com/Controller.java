package com;

import java.net.Socket;

public class Controller {
    public Object function(Socket socket, String s) {
        for (int i = 0; i < ClientInfo.clientList.size(); i++) {
            if (ClientInfo.clientList.get(i).getSocket().equals(socket)) {
                if (ClientInfo.clientList.get(i).isAuth()) {
                    if (JsonParse.toMap(s).get("order").toString().equals("\"1023\"")) {
                        String[] str = {String.valueOf(JsonParse.toMap(s).get("serialNumber")), String.valueOf(System.currentTimeMillis() / 1000)};
                        return (Object) Msgcode.execute(1023, str);
                    } else if (JsonParse.toMap(s).get("order").toString().equals("\"1031\"")) {
                        String[] str = {String.valueOf(JsonParse.toMap(s).get("loc"))};
                        return (Object) Msgcode.execute(1031, str);
                    } else if (JsonParse.toMap(s).get("order").toString().equals("\"1050\"")) {
                        String[] str = {String.valueOf(JsonParse.toMap(s).get("serialNumber"))};
                        return (Object) Msgcode.execute(1050, str);
                    } else if (JsonParse.toMap(s).get("order").toString().equals("\"1060\"")) {
                        String[] str = {String.valueOf(JsonParse.toMap(s).get("ban")), String.valueOf(JsonParse.toMap(s).get("id"))};
                        return (Object) Msgcode.execute(1060, str);
                    }
                } else {
                    if (JsonParse.toMap(s).get("order").toString().equals("\"1000\"")) {
                        String[] str = {String.valueOf(JsonParse.toMap(s).get("serialNumber"))};
                        for (Client c : ClientInfo.clientList) {
                            if (c.getSocket().equals(String.valueOf(JsonParse.toMap(s).get("socket")))) {
                                c.setSerialnumber(str[0]);
                                c.setId(String.valueOf(JsonParse.toMap(s).get("id")));
                            } else {
                                return (Object) 1005;
                            }
                        }
                        return Msgcode.execute(1000, str);
                    } else if (JsonParse.toMap(s).get("order").toString().equals("\"1010\"")) {
                        System.out.println("1010");
                        String[] str = {String.valueOf(JsonParse.toMap(s).get("id")), String.valueOf(JsonParse.toMap(s).get("serialNumber"))};
                        return (Object) Msgcode.execute(1010, str);
                    } else {
                        return -1;
                    }
                }
            } else {
                return -1;
            }
        }
        return -1;
    }

}
