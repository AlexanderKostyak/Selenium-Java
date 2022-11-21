import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.TimeUnit;
//Remember to link the path for the selenium api in JGrasp Settings -> PATH/CLASSPATH -> Workspace -> CLASSPATHS -> New -> Directory Path or JAR File -> Browse
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class wiki 
{
	public static void main(String[] args)
	{
        System.setProperty("webdriver.chrome.driver","webdriver\\chromedriver.exe");   //make sure this path points to the chromedriver.exe file from where this file is saved
        WebDriver driver = new ChromeDriver();

        String baseUrl = "https://en.wikipedia.org/wiki/Everything";
        driver.get(baseUrl);
        
            clicknext(driver);

            driver.quit();
	}
	
	
	public static void clicknext(WebDriver driver) {
		while(true)
        {	  
	      
			  List<WebElement> tmp1 = driver.findElements(By.cssSelector("p > a"));
			  List<WebElement> tmp2 = driver.findElements(By.cssSelector("a[href^=\"/wiki\"]"));
			  
			  List<WebElement> tmp = new ArrayList<WebElement>();
			
			  for (int i = 0 ; i < tmp1.size() ; i++)
			  {
				  for (int j = i ; j < tmp2.size() ; j++)
				  {
					  if (tmp1.get(i).equals(tmp2.get(j)))
					     tmp.add((WebElement)tmp1.get(i));
					  
				  }
			  }
			  
           
			  driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
           //Wait ~about 2 seconds
           try {
				  Thread.sleep(2000);
			  } catch (InterruptedException e) {
				  e.printStackTrace();
			  }
            
           int randomNum = 0 + (int)(Math.random() * tmp.size());
         	  
           try 
           {  
               int i = 0;
			  
               while (tmp.get(randomNum).getText().indexOf(']', 0) > 0 || !tmp.get(randomNum).isDisplayed())
				   {
                  i++;
					   randomNum = 0 + (int)(Math.random() * tmp.size()*0.9);
					   
                  if (i>10) //if ten randoms could not find an element that met the above conditions, try every array element
					   {
						   for (randomNum=0 ; randomNum<tmp.size() ; randomNum++)
							   if (tmp.get(randomNum).getText().indexOf(']', 0) > 0 || !tmp.get(randomNum).isDisplayed() )
								   randomNum++;
					   }
				  }  
				  
              //if an element passed the conditions or the whole array has been tried, click the current index.  if nothing is found, an unclickable element may be clicked, raising an exception
              tmp.get(randomNum).click();
               
           } catch (Exception e) 
           {
			      e.printStackTrace();
				   //driver.get(differentURL); //you could fill an array with the past few URL's, and go back a few if you get here
           }
         	  
           //closes tab pop ups
           if (driver.getWindowHandles().size()>1)
           {
               driver.close();
         	   //driver.get(differentURL);          		  
           }   
        } 
	}
}
