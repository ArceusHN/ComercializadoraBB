/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Administrator
 */
public class Conexion {
    String url = "jdbc:mysql://localhost:3306/comerciodb?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String user = "root";
    String password ="";
    private Connection connect;
    
    public Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
        }
        catch(Exception e){
            System.out.println("ERROR CONEXION" + e);
        }
    }
    
    public Connection getConnection(){
        return this.connect;
    }
}
