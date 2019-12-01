/* Eva Curry
 * CSC 201 Fall 2019
 * Project 4 - Popcorn
 * 11/12/2019
 */
 
import java.io.*;
import java.util.*;

public class Popcorn {

	// This method splits a string up by comma delimiter and creates an array, 
	// then returns the farmName variable based on string data position [0] of the array
	
	public static String farmName(String userInput)
	{
		String[] splitData = userInput.split(",");		
		
		String farmName = splitData[0];
		
		String spacer = "";
		
		for (int i = 1; i <= 30 - farmName.length(); i++)
		{
			spacer = spacer + " ";
		}	
		
		farmName = farmName + spacer;
		
		return farmName;		
	}	
	
	// This method repeats previous method, but takes array position [1] content
	// and splits it into a new array this time using white space delimiter.
	// Method then performs math on the contents of the new array and uses if statements
	// and loops to create proper placement of star marks, white space, and goal marks in bar chart
	
	public static String pintsPerAcre(String userInput)
	{
		String[] splitData = userInput.split(",");
		
		splitData[1] = splitData[1].trim();
	
		String[] splitNumbers = splitData[1].split(" ");
		
		// I have put this for loop in so that I can use a single space delimiter instead of the double space.
		// This just replaces the erroneous array empty position with the next value in the array so the numbers
		// wind up in the expected position whether the file has single spaces or double spaces.
		
		for (int i = 0; i < splitNumbers.length; i++)
		{
			if (splitNumbers[i].equals(""))
			{
				int pos = i;
				for (int j = pos; j < splitNumbers.length - 1; j++)
				{
					splitNumbers[j] = splitNumbers[j+1];
				}
			}
		}			
	
		// Convert from String to double & int	
			
		double acres = Double.parseDouble(splitNumbers[0]);		
		int pints = Integer.parseInt(splitNumbers[1]);
		
		// Pints per acre ( x 25 ) math
		
		double pintsPerAcreX25 = Math.round(pints / acres / 25);
			
		// A loop to count star marks prior to goal column
		
		String pints25 = "";
		
		for (int i = 0; i < pintsPerAcreX25 && i < 15; i++)
		{
			pints25 = pints25 + "*";
		}		
		
		// An if statement to mark # in goal column and add marks if pints produced exceeds goal column
		
		String extraPints = "";
		
		if (pintsPerAcreX25 > 15)
		{
			for (int i = 0; i < pintsPerAcreX25 - 16; i++)
			{
				extraPints = extraPints + "*";
			}
			pints25 = pints25 + "#" + extraPints;		
		}
		
		// An if statement to place pipe in goal column if goal is not reached
		
		String spacer = "";		
		
		if (pintsPerAcreX25 < 16)
		{
			for (int i = 0; i < 15 - pintsPerAcreX25; i++)
			{
				spacer = spacer + " ";
			}
			pints25 = pints25 + spacer + "|";		
		}
			
		return pints25;
	}
	
	public static void main(String[]args) throws IOException
	{		
		// Prompts the user input a file name
		
		System.out.println("Please enter the name of the text file to see the included data as a bar chart:"); 
		Scanner scan = new Scanner(System.in);
		String fileName = scan.next();
		
		FileReader fr = new FileReader(fileName);
		Scanner scanFile = new Scanner(fr);		
		
		System.out.println("                   Popcorn Co-op");
		System.out.println();
		System.out.println("                              Production in Hundreds");
		System.out.println("                              of Pint Jar per Acre");
		System.out.println("Farm Name                        1   2   3   4   5   6");
		System.out.println("                              ---|---|---|---|---|---|---");
									
		while (scanFile.hasNextLine())
		{
			String userInput = scanFile.nextLine();
						
			if ( ! userInput.isEmpty()) // if the boolean is FALSE -- while each line has some sort of content.
			{			
				System.out.println(farmName(userInput) + pintsPerAcre(userInput));
			}	
		}
		
		scanFile.close();
		scan.close();
	}
}
