/* 
Name: TA and Jeremy Mwangelwa
Group Number: 8
Course: CET 350 - 001 Technical Computing with Java
Spring 2016

This program calculates the
average value of numbers entered */

//For input and output
import java.io.*;

class Average {
	
	
	public static void main(String args[]) throws Exception
	{
		//Will catch keyboard input error if it should occur and force program to quit
		try {
		//Declare for counting, totaling grades and receiving grades
		int count = 0;
		double totalGrade = 0.0, grade = 0.0;
		
		
		//User directions
		System.out.println("Enter grade no: " + (count+1) + " from 0 to 100. Enter anything else to quit.");
				
		//Create keyboard input stream
		String input;
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));		
		input = buffReader.readLine();
		
		/*Convert data in input stream from String to double with
		number format exception handling*/
		try{
		//Convert string to double
		grade = Double.parseDouble(input);
		
			//Count the grades and total them up as long as they are numbers from 0 thru 100
			while ((grade>=0)&&(grade<=100)) {
				count++;
				totalGrade=totalGrade+grade;		
			
			System.out.println("Enter grade no: " + (count+1) + " from 0 to 100. Enter anything else to quit.");
			input = buffReader.readLine();
			
			//Quit if entry is not a number or not a number between 0 thru 100
			try{
			grade = Double.parseDouble(input);
			} catch (NumberFormatException errorObject) {
				System.out.println("QUITTING\n");
				break; }
			}
		
		} catch (NumberFormatException errorObject) {
		//Catches exception and prints statement
		System.out.println("QUITTING\n"); }
		
		//Prints totals and average
		System.out.println("Number of Grades: " + count);
		System.out.println("Total of Grades: " + totalGrade);
		Double average = new Double(totalGrade/count);
		//Tests NaN in case of division by 0
		if (average.isNaN()) 
		System.out.println("Average: 0");
		else {
		System.out.println("Average: " + average); }		
		}
		//Catches possible keyboard failure
		catch (IOException ioError) {
			System.out.println("Keyboard Error. Unable to Continue.");
		}
	}
}