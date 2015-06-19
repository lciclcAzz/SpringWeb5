package com.idc.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conDB {
	/*
 public static void main(String args[]) {

    Connection con = null;

    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      con = DriverManager.getConnection("jdbc:mysql:///db_merchant","root", "asdf");

      if(!con.isClosed())
        System.out.println("Successfully connected to " +  "MySQL server using TCP/IP...");

    } catch(Exception e) {
      System.err.println("Exception: " + e.getMessage());
    } finally {
      try {
        if(con != null)
          con.close();
      } catch(SQLException e) {}
    }
  }
 */
//	public Connection getConnection() //throws ClassNotFoundException
//	{
	public static Connection getConnection()  {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			return  DriverManager.getConnection("jdbc:mysql://localhost:3306/db_merchant","root", "asdf");
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	  
}