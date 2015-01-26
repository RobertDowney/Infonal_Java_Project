package com.engin.yildirim.MarsRover;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class App 
{

    static ArrayList<Integer> positionsX = new ArrayList<Integer>();  // create ArrayList for Positions-X of rovers
    static ArrayList<Integer> positionsY = new ArrayList<Integer>();  // create ArrayList for Positions-Y of rovers
    static ArrayList<String> directions = new ArrayList<String>();    // create ArrayList for Directions of rovers
    static ArrayList<String> instructions = new ArrayList<String>();  // create ArrayList for Instructions of rovers

	public static void main (String[] args) throws java.lang.Exception
	{
		App.readFile("input.txt"); // Read Text File
		PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		int size = instructions.size();   // Calculate instructions number 
		for(int i=0; i<size; i++)
		{
			roverCycle(i);	 // runs number of times of Rovers entered by the user
		}
		writer.close();
	}
	
	private static void roverCycle(int roverNumber) 
	{
		// TODO Auto-generated method stub
		
		int posX= positionsX.get(roverNumber);   // get position-X of the rover
		int posY= positionsY.get(roverNumber);   // get position-Y of the rover
		String direction= directions.get(roverNumber);    // get direction of the rover
		String instruction = instructions.get(roverNumber);   // get instruction of the rover

		char instructionArray[]=instruction.toCharArray();  // create new charArray to apply instructions 
		
		for(int i=0; i<instructionArray.length; i++)
		{
			switch (instructionArray[i]) 
			{
				case 'M':   // Case Move
					switch (direction) 
					{	
						case "N": posY++;    // Change positions according to direction cases
							break;
						case "E": posX++;
							break;
						case "S": posY--;
							break;
						case "W": posX--;
							break;
					}
					break;
					
				case 'L': 	// Case Left				
					switch (direction) 
					{	
						case "N": direction="W";  // Change directions according to direction cases
							break;
						case "E": direction="N";
							break;
						case "S": direction="E";
							break;
						case "W": direction="S";
							break;
					}
					break;						

				case 'R':  // Case Right
					switch (direction) 
					{	
						case "N": direction="E";   // Change directions according to direction cases
							break;
						case "E": direction="S";
							break;
						case "S": direction="W";
							break;
						case "W": direction="N";
							break;
					}
					break;		
			
			}
		}
		

		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)))) 
		{
		    out.println(posX + " " + posY + " " + direction);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
		
	}

	public static void readFile(String URL)
	{
	    File file = new File(URL);
	 
	    try 
	    {
			BufferedReader reader = null;
	        reader = new BufferedReader(new FileReader(file));
	        
	        int[] lowerLeft= new int[2];
            String[] line1 = reader.readLine().split(" ");
            lowerLeft[0]=Integer.parseInt(line1[0]);
            lowerLeft[1]=Integer.parseInt(line1[1]);
	           
            
	        String line = reader.readLine();
	        

	        while (line!=null) 
	        {
	            String[] position = line.split(" ");
	            positionsX.add(Integer.parseInt(position[0]));
	            positionsY.add(Integer.parseInt(position[1]));
	            directions.add(position[2]);

	            line = reader.readLine();
	            
	            instructions.add(line);	
	            
	            line = reader.readLine();

	            
	        }
        
	        reader.close(); 
	    } 
	    catch (FileNotFoundException e) 
	    {
	        // TODO: handle exception
	        System.out.println("File is not found!");
	    } 
	    catch (IOException e)
	    {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}


}
