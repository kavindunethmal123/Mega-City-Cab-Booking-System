package com.MagaCityCab.utils;

import java.sql.Connection;

public class TestDBConnection {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("✅ Database Connection Successful!");
        } else {
            System.out.println("❌ Database Connection Failed!");
        }
    }
}
