package ekt;
import java.io.FileFilter;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
			//System.out.println("Tom Tabel?");
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
	
	void sessionLogin() {
		try {
			String harBrukerQ;
			while (!((harBrukerQ = Svar("Har du en bruker?[J/N]").toUpperCase()).equals("J")|| harBrukerQ.equals("N"))) {}
				
			if (harBrukerQ.equals("J")){
				int Svar = Integer.parseInt(Svar("Hva er ID nummeret ditt?"));
				String fire = (Svar("Fire siffer"));
				boolean isTrue = verifyPerson(Svar, fire);
				if (isTrue) {
					regTrainingSession(Svar);
				}else {
					System.out.println("Login Error: Wrong password");
				}
				
				
			}else {
				int ID = regPerson();
				System.out.println(Colors.ANSI_RED+"[ID: "+ID+" ] Husk ID-koden på brukeren din\033[0m"+Colors.ANSI_RESET);
			}
			
		} catch (Exception e) {
			System.out.println("Dette gikk ikke");
		}
	}
	
	void regTrainingSession(int brukerID){
		
	}
	
	
	
	
	int regPerson() {
		try {
        	String navn = Svar("Hva heter du?");
        	int fire = Integer.parseInt(Svar("Fire siffer"));
			PreparedStatement regApp = conn.prepareStatement("INSERT INTO Person VALUES ((?),(?),(?))");
			int ID = lastObjectInTable("Person");
			regApp.setInt(1, ID);
			regApp.setString(2, navn);
			regApp.setInt(3, fire);
	        regApp.execute();
	        return ID;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Det gikk ikke å sette innn personen");
			return -1;
		}
		
	}
	
	boolean verifyPerson(int ID, String kode) {
		try {
			String kode1 = "";
			PreparedStatement verPer = conn.prepareStatement("SELECT * FROM Person WHERE PersonID=(?)");
			verPer.setInt(1, (ID));
			ResultSet r = verPer.executeQuery();
	        while(r.next()) {
	        	kode1 = r.getString("fireSiffer");
	        }
	        //System.out.println(kode1.getClass()+" | "+kode.getClass());
	        //System.out.println(kode1.equals(kode));
	        return kode1.equals(kode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Det gikk ikke å sette inn personen");
			return false;
		}
	}
	
	
	void test() {
        try {
        	float len = 22;
        	System.out.println("[Registreing av økt]");
			PreparedStatement regApp = conn.prepareStatement("INSERT INTO Person VALUES ((?),(?),(?),(?),(?),(?))");
			regApp.setInt(1, lastObjectInTable("TrainingSession"));
			regApp.setInt(2, 4);
			int dag = Integer.parseInt(Svar("Dag"));
			int måned = Integer.parseInt(Svar("Måned"));
			int år = Integer.parseInt(Svar("År"))+1900;
			@SuppressWarnings("deprecation")
			
			DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;

			String formattedDate = formatter.format(LocalDate.now());
			System.out.println(formattedDate);
			regApp.setDate(3, formattedDate);
			regApp.setFloat(4, len);
			regApp.setString(5, "FIT");
			regApp.setString(6, "NICE");
	        regApp.execute();
	        System.out.println("Funket kanskje?");
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
