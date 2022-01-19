package com.dataeval.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

public class TestReadData {
	
	public static void main(String[] args) throws IOException {
		Map<String , String> errors=new HashMap<String, String>();
		 CSVReader reader = new CSVReader(new FileReader("/Users/sairamdikondawar/tagnos/tasks/test_errors.csv"), ',' , '"' , 1);
	       
	      //Read CSV line by line and use the string array as you want
	      String[] nextLine;
	      while ((nextLine = reader.readNext()) != null) {
	         if (nextLine != null) {
	            //Verifying the read data here
//	            System.out.println(Arrays.toString(nextLine));
	            
	            String totalError=Arrays.toString(nextLine);
	            
	            String[] ttErrorAry=totalError.split(";");
	            
	            for(String ss:ttErrorAry)
	            {
	            	if(ss.startsWith("[error") || ss.startsWith("error") || ss.startsWith("[warning") || ss.startsWith("warning"))
	            	{
	            		if(ss.contains("gw.validation"))
	            		{
//	            			System.out.println(ss.split("gw.validation")[0]);
	            			errors.put(ss.split("gw.validation")[0].trim(), ss.split("gw.validation")[0]);
	            			
	            		}else {
//	            			System.out.println(ss);
	            			errors.put(ss.trim(), ss);
	            		}
	            	}
	            }
	           
//	           
	         }
	         
	         
	       }
	      
	      for(String ss:errors.keySet())
	      {
	    	  System.out.println(ss);
	      }
	      
	      System.out.println(errors.size()); 
	   }
		
	

}
