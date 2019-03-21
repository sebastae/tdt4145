/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ekt;

import java.sql.*;
import java.util.Properties;



public abstract class DBConn {
	String username = "taramire_gruppe";
	String password = "ramirez160796";
	String db = "taramire_dbDel2";
	protected Connection conn;
    public DBConn () {
    }
    public void connect() {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Properties for user and password. Here the user and password are both 'paulr'
            Properties p = new Properties();
            p.put("user", username);
            p.put("password", password);           
	    //            conn = DriverManager.getConnection("jdbc:mysql://mysql.ansatt.ntnu.no/sveinbra_ektdb?autoReconnect=true&useSSL=false",p);
            conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no/"+db+"?autoReconnect=true&useSSL=false",p);
            System.out.println("Connected to the database ["+db+"]");
        } catch (Exception e)
    	{
            throw new RuntimeException("Unable to connect", e);
    	}
    }
}