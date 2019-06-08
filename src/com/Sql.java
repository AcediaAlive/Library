package com;

public class Sql {
    static String getSerialNumber = "SELECT \"public\".student.\"serialnumber\" FROM \"public\".student WHERE \"public\".student.serialnumber LIKE ?";
    static String createAccount = "INSERT INTO \"public\".\"student\" (\"id\", \"serialnumber\", \"ban\") VALUES (?,?,0)";
    static String getBan = "SELECT \"public\".student.\"ban\" FROM \"public\".student WHERE \"public\".student.id LIKE ?";
    static String setBan = "UPDATE \"public\".\"student\" SET \"ban\"=? WHERE (\"id\"=?)";
    static String getUse = "SELECT \"public\".seat.use FROM \"public\".seat WHERE \"public\".seat.loc LIKE ?";
    static String deleteAccount = "DELETE FROM \"public\".\"student\" WHERE (\"serialnumber\"=?)";
    static String setEndTime = "UPDATE \"public\".\"seat\" SET \"endtime\"=? WHERE (\"loc\"=?)";
    static String seat = "SELECT \"public\".seat.*FROM \"public\".seat";
    static String log = "SELECT \"public\".log.* FROM \"public\".log";
    static String getEndTime = "SELECT \"public\".seat.endtime FROM \"public\".seat WHERE \"public\".seat.loc LIKE ?";
    static String getLoc = "SELECT \"public\".student.loc FROM \"public\".student WHERE \"public\".student.serialnumber LIKE ?";
    static String getId = "SELECT \"public\".student.\"id\" FROM \"public\".student WHERE \"public\".student.serialnumber LIKE ?";
}
