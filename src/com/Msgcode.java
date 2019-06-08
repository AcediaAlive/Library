package com;

import java.util.ArrayList;

public class Msgcode {
    int msgcode = -1;

    public String getMsg(int msgcode) {
        switch (msgcode) {
            case 1000:
                return "login";
            case 1001:
                return "login succeeded";
            case 1002:
                return "login failed";
            case 1003:
                return "login failed, do not have an account";
            case 1004:
                return "login failed, id is banned";
            case 1005:
                return "login failed, initialization failed";
            case 1010:
                return "create";
            case 1011:
                return "create succeeded";
            case 1012:
                return "create failed";
            case 1013:
                return "create failed, id is not unique";
            case 1014:
                return "create failed, id is banned";
            case 1022:
                return "pause failed";
            case 1023:
                return "test expire";
            case 1024:
                return "expired";
            case 1025:
                return "unexpired";
            case 1030:
                return "seat";
            case 1031:
                return "choice";
            case 1032:
                return "seat available";
            case 1033:
                return "seat occupied";
            case 1034:
                return "seat do not exist";
            case 1035:
                return "random";
            case 1040:
                return "alive";
            case 1041:
                return "online";
            case 1042:
                return "dropped";
            case 1050:
                return "delete";
            case 1051:
                return "delete succeeded";
            case 1052:
                return "delete failed";
            case 1060:
                return "ban";
            case 1061:
                return "ban succeeded";
            case 1062:
                return "ban failed";
            case 1070:
                return "check";
            case 1071:
                return "check succeeded";
            case 1072:
                return "check failed";
        }
        return "unknown";
    }

    public static int execute(int msgcode, String[] s) {
        // TODO: 2019/4/14 parameter count
        switch (msgcode) {
            case 1000:
                return login(s);
            case 1010:
                return create(s);
            case 1023:
                return expire(s);
            case 1031:
                return choice(s);
            case 1040:
                return alive();
            case 1050:
                return delete(s);
            case 1060:
                return ban(s);
        }
        return -1;
    }

    public static ArrayList<String[]> execute(int msgcode, String[] s, int needArraryList) {
        switch (msgcode) {
            case 1030:
                return seat(s);
            case 1070:
                return check(s);
        }
        return null;
    }

    public static int login(String[] s) {
        if (Database.query(Sql.getSerialNumber, s).size() == 1) {
            if (Database.query(Sql.getBan, s).get(0)[0].equals("0")) {
                return 1001;
            } else {
                return 1004;
            }
        } else {
            return 1003;
        }
    }

    public static int create(String[] s) {
        if (Database.update(Sql.createAccount, s) > 0) {
            return 1011;
        } else {
            return 1012;
        }
    }


    public static int expire(String[] s) {
        String s2 = s[s.length - 1];
        String[] s3 = new String[s.length - 1];
        for (int i = 0; i < s3.length; i++) {
            s3[i] = s[i];
        }
        String[] s4 = {Database.query(Sql.getLoc, s3).get(0)[0]};
        if (Database.query(Sql.getEndTime, s4).get(0)[0].equals(s2)) {
            return 1024;
        } else {
            return 1025;
        }
    }

    public static ArrayList<String[]> seat(String[] s) {
        ArrayList<String[]> a = Database.query(Sql.seat, s);
        return a;
    }

    public static int choice(String[] s) {
        if ((Database.query(Sql.getUse, s).size() < 1)) {
            return 1034;
        } else if (Database.query(Sql.getUse, s).get(0)[0].equals("0")) {
            String[] s2 = {String.valueOf(System.currentTimeMillis() / 1000 + 1800), s[0]};
            if (!(Database.update(Sql.setEndTime, s2) > 0)) {
                return 1022;
            }
            return 1032;
        } else {
            return 1033;
        }
    }

    public static int alive() {
        return -1;
    }

    public static int delete(String[] s) {
        if (Database.update(Sql.deleteAccount, s) > 0) {
            return 1051;
        } else {
            return 1052;
        }
    }

    public static int ban(String[] s) {
        if (Database.update(Sql.setBan, s) > 0) {
            return 1061;
        } else {
            return 1062;
        }
    }

    public static ArrayList<String[]> check(String[] s) {
        ArrayList<String[]> a = Database.query(Sql.log, s);
        return a;
    }
}
