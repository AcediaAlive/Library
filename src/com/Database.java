package com;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/libra";
    private static final String NAME = "postgres";
    private static final String PASSWORD = "baiyu000";

    public static void main(String[] args) throws Exception {
        //1.加载驱动程序
        Class.forName("org.postgresql.Driver");
    }

    public static ArrayList<String[]> query(String s, String[] parm) {
        ArrayList<String[]> result = new ArrayList<String[]>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
            Pattern p = Pattern.compile("\\?", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(s);
            int count = 0;
            while (m.find()) {
                count++;
            }
            if (count != parm.length) {
                System.out.println("参数数量不匹配");
            } else {
                PreparedStatement ps = conn.prepareStatement(s);
                for (int i = 0; i < count; i++) {
                    ps.setString(i + 1, parm[i]);
                    System.out.println("parameter: " + parm[i] + " " + i);
                }
                System.out.println("指令:" + s);
                System.out.println("参数数量" + count);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rd = rs.getMetaData();
                int a = rd.getColumnCount();
                while (rs.next()) {
                    String[] temp = new String[a];
                    for (int i = 0; i < a; i++) {
                        temp[i] = rs.getString(i + 1);
                    }
                    result.add(temp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int update(String s, String[] parm) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
            Pattern p = Pattern.compile("\\?", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(s);
            int count = 0;
            while (m.find()) {
                count++;
            }
            if (count != parm.length) {
                System.out.println("参数数量不匹配");
            } else {
                PreparedStatement ps = conn.prepareStatement(s);
                for (int i = 0; i < count; i++) {
                    ps.setString(i + 1, parm[i]);
                }
                int ca = ps.executeUpdate();
                return ca;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
