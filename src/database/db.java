/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author remo
 */
public class db {
    private String url = "jdbc:mysql://localhost:3306/wazzup";
    private String username = "root";
    private String password = "root";
    
    private Connection cnx;
    private static db instance;
    
    public db() {
        try {
            cnx = DriverManager.getConnection(url, username, password);
            System.out.println("db connected");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    public static db getInstance() {
        if(instance == null){
            instance = new db();
        }
        return instance;
    }
    
    
    
    
    
}
