/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ekt;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	InsertINFO test = new InsertINFO();
    	test.connect();
    	while (true) {
    		System.out.println("\nOption 1: Registrere et apparat");
    		System.out.println("\nOption 2: Registrere et Treningsøkt");
    		System.out.print("\n> ");
    	    Scanner scanner = new Scanner(System.in);
    	    String choice = scanner.nextLine();
    	    //System.out.println(choice.getClass());
    	    if (choice.equals("1")) {
    	    	test.regApp();
    	    }else if(choice.equals("2")){
    	    	test.regTrainingSession();
    	    }
    	}
    }
}