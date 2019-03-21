package ekt;
import java.io.FileFilter;
import java.sql.*;
import java.util.Scanner;


public class InsertINFO extends DBConn {
	private PreparedStatement regStatement;
	
	/*  1)Register equipment, exercises and workout with associated data.
		2)Get information about the n latest workouts with theirnotes, where n is chosen by theuser.
		3)For every exercise, retrieve a result log for a given time interval where the endpointsof the interval 
		is specified by the user.
		4)Create new exercise groups and find similar exercises.
		5)A self-chosen use case
	 */
	
	
	int lastObjectInTable(String tabelNavn) {
		try {
			int data = -1;
			Statement lastID = conn.createStatement();
			String text = ("SELECT * FROM "+tabelNavn+" ORDER BY "+tabelNavn+"ID DESC LIMIT 1");
			ResultSet rs = lastID.executeQuery(text);
			while (rs.next()){
				data = rs.getInt(tabelNavn+"ID");
			}
			System.out.println(data);
			return data+1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Tom Tabel?");
			return 0;
		}
	}
	
	void regApp() {
		String appName=Svar("Apparat");
		String text=Svar("Beskrivelse");
		try {
			 PreparedStatement regApp = conn.prepareStatement("INSERT INTO Apparatus VALUES ((?),(?),(?))");
			 regApp.setInt(1, lastObjectInTable("Apparatus"));
			 regApp.setString(2, appName);
			 regApp.setString(3, text);
	         regApp.execute();
	         System.out.println("Maskinen "+appName+" er satt inn i DB");
	         
			
		} catch (Exception e) {
			System.out.println("Å sette inn "+appName+" inn i Apparatus gikk ikke");
		}
	}
	
	void regTrainingSession() {
		try {
			String harBrukerQ;
			while (!((harBrukerQ = Svar("Har du en bruker?[J/N]").toUpperCase()).equals("J")|| harBrukerQ.equals("N"))) {}
				
			if (harBrukerQ.equals("J")){
				int Svar = Integer.parseInt(Svar("Hva er ID nummeret ditt?"));
				int fire = Integer.parseInt(Svar("Fire siffer"));
				boolean isTrue = verifyPerson(Svar, fire);
				if (isTrue) {
					//
				}else {
					System.out.println("Login Error");
				}
				
				
			}else {
				regPerson();
			}
			
		} catch (Exception e) {
			System.out.println("Dette gikk ikke");
		}
	}
	
	
	
	
	void regPerson() {
		try {
        	String navn = Svar("Hva heter du?");
        	int fire = Integer.parseInt(Svar("Fire siffer"));
			PreparedStatement regApp = conn.prepareStatement("INSERT INTO Person VALUES ((?),(?),(?))");
			regApp.setInt(1, lastObjectInTable("Person"));
			regApp.setString(2, navn);
			regApp.setInt(3, fire);
	        regApp.execute();
	        System.out.println("Personen "+navn+" er satt inn i DB");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Det gikk ikke å sette innn personen");
		}
	}
	
	boolean verifyPerson(int ID, int kode) {
		try {
			int kode1 = 0;
			PreparedStatement verPer = conn.prepareStatement("SELECT * FROM Person WHERE PersonID=(?)");
			verPer.setInt(1, (ID));
			ResultSet r = verPer.executeQuery();
	        while(r.next()) {
	        	kode1 = r.getInt("fireSiffer");
	        }
	        System.out.println(kode1);
	        return kode1==kode;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Det gikk ikke å sette innn personen");
			return false;
		}
	}
	
	
	void test() {
        try {
        	String navn = Svar("Hva heter du?");
        	int fire = Integer.parseInt(Svar("Fire siffer"));
			PreparedStatement regApp = conn.prepareStatement("INSERT INTO Person VALUES ((?),(?),(?))");
			regApp.setInt(1, lastObjectInTable("Person"));
			regApp.setString(2, navn);
			regApp.setInt(3, fire);
	        regApp.execute();
	        System.out.println("Personen "+navn+" er satt inn i DB");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Det gikk ikke å sette innn personen");
		}
	}
	
	String Svar(String question) {
    	Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println(question+": ");
	    String appName = myObj.nextLine();  // Read user input
	    return appName;
	}

}
