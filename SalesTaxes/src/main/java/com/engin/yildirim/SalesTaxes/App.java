package com.engin.yildirim.SalesTaxes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class App 
{
	static ArrayList<Integer> numberOfGoods = new ArrayList<Integer>();  // create ArrayList for number of goods.
    static ArrayList<Boolean> isExempt = new ArrayList<Boolean>();      // create ArrayList for Exempt.
    static ArrayList<Boolean> isImported = new ArrayList<Boolean>();   // create ArrayList for Imported
    static ArrayList<String> nameOfGoods = new ArrayList<String>();   // create ArrayList for name of goods.
    static ArrayList<Double> prices = new ArrayList<Double>();       // create ArrayList for prices.

	public static void main (String[] args) throws java.lang.Exception
	{
		App.readFile("Input3.txt"); // Read Text File
		PrintWriter out = new PrintWriter("output.txt", "UTF-8");
		int size = numberOfGoods.size();   // Calculate number of goods.
		double salesTaxes=0;
		double total=0;
		for(int i=0; i<size; i++)
		{
			double price = taxCycle(i);	 
			total += price;								// calculate total price
			salesTaxes += (price - prices.get(i));      // calculate total salesTaxes
		    out.printf(numberOfGoods.get(i) + " " + nameOfGoods.get(i) + " : " );
			out.printf("%.2f", price);
			out.println();
		}
		
		out.printf("Sales Taxes: %.2f", salesTaxes);
		out.println();
		out.printf("Total: %.2f", total);
		out.println();
		out.close();
	}
	
	private static double taxCycle(int taxNumber) 
	{
		// TODO Auto-generated method stub
		double price=prices.get(taxNumber);
		
		if(!isExempt.get(taxNumber))
			price *= 1.1; 
		if(isImported.get(taxNumber))
			price *= 1.05;
		
		return price;	
	}

	public static void readFile(String URL)
	{
	    File file = new File(URL);
	 
	    try 
	    {
			BufferedReader reader = null;
	        reader = new BufferedReader(new FileReader(file));
         
	        String line = reader.readLine();
	        
	        while (line!=null) 
	        {
		        String[] lineParts = line.split(" ");
		        numberOfGoods.add(Integer.parseInt(lineParts[0]));                // get first element of line (Ex: 1)
		        prices.add(Double.parseDouble(lineParts[lineParts.length-1]));   // get last element of line (Ex:12.49)
	            
		        String name=lineParts[1];
		        for(int i=2; i<lineParts.length-2; i++)
		        {
		        	name = name + " " + lineParts[i];   // get item name
		        }
		        nameOfGoods.add(name);     // add name to nameOfGoods ArrayList
		        
		        boolean isImprtd=false;
	        	boolean isExmpt=false;
	        	
		        for(int i=0; i<lineParts.length; i++)
		        {
		        	    	
		        	switch(lineParts[i])
		        	{
		        		case "imported": isImprtd=true;  // if linePart's value equal to "imported"
		        			break;
		        		case "food": isExmpt=true;      // if linePart's value equal to "food"
        					break;
		        		case "book": isExmpt=true;     // if linePart's value equal to "book"
        					break;
		        		case "medical": isExmpt=true;  // if linePart's value equal to "medical"
        					break;
		        	
		        	}
		        }
		        
		        if(isImprtd)
			        isImported.add(true);   // set isImported ArrayList's value.
		        else
			        isImported.add(false);
		        
		        if(isExmpt)
		        	isExempt.add(true);   // set isExmpt ArrayList's value.
		        else                         
		        	isExempt.add(false);
		        
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
