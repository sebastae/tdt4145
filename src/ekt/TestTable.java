package ekt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class TestTable extends DBConn {


	boolean test(){
		try {
			//System.out.println("TEST");
            PreparedStatement navn = conn.prepareStatement("select * from User");;
            ResultSet rs = navn.executeQuery();
            while (rs.next()) {
            	System.out.println(rs.getString("UserID"));            	
            }
            return true;

        } catch (Exception e) {
            System.out.println("db error during select of loperposter = "+e);
            return false;
        }
	}
}
