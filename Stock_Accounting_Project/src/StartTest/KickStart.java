package StartTest;



import org.testng.annotations.Test;

import DriverScriptFactory.DriverScript;

public class KickStart
{
	@Test
 public void kickStart() throws Throwable
 {
		DriverScript start=new DriverScript();
		try{
		start.startTest();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
 }	
}
