package com.dataeval.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestReadData {
	
//	public static void main(String[] args) throws IOException {
//		Map<String , String> errors=new HashMap<String, String>();
//		 CSVReader reader = new CSVReader(new FileReader("/Users/sairamdikondawar/tagnos/tasks/test_errors.csv"), ',' , '"' , 1);
//	       
//	      //Read CSV line by line and use the string array as you want
//	      String[] nextLine;
//	      while ((nextLine = reader.readNext()) != null) {
//	         if (nextLine != null) {
//	            //Verifying the read data here
////	            System.out.println(Arrays.toString(nextLine));
//	            
//	            String totalError=Arrays.toString(nextLine);
//	            
//	            String[] ttErrorAry=totalError.split(";");
//	            
//	            for(String ss:ttErrorAry)
//	            {
//	            	if(ss.startsWith("[error") || ss.startsWith("error") || ss.startsWith("[warning") || ss.startsWith("warning"))
//	            	{
//	            		if(ss.contains("gw.validation"))
//	            		{
////	            			System.out.println(ss.split("gw.validation")[0]);
//	            			errors.put(ss.split("gw.validation")[0].trim(), ss.split("gw.validation")[0]);
//	            			
//	            		}else {
////	            			System.out.println(ss);
//	            			errors.put(ss.trim(), ss);
//	            		}
//	            	}
//	            }
//	           
////	           
//	         }
//	         
//	         
//	       }
	      
//	      for(String ss:errors.keySet())
//	      {
//	    	  System.out.println(ss);
//	      }
//	      
//	      System.out.println(errors.size()); 
//	   }
		
	
	public static void main(String[] args) {
		String admin="admin";
		
		BCryptPasswordEncoder bb=new BCryptPasswordEncoder();
		String pwd1=bb.encode(admin);
		String pwd2=bb.encode(admin);
		System.out.println(	pwd1);
		System.out.println(pwd2);
		boolean match=bb.matches(admin, pwd2);
		System.out.print(match);
		
	}
	

}
