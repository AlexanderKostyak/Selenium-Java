import java.util.ArrayList;
import java.util.List;

//Remember to link the path for the selenium api in Settings -> PATH/CLASSPATH -> Workspace -> CLASSPATHS -> New -> Directory Path or JAR File -> Browse
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GetElements 
{
	public static void main(String[] args)
	{

        System.setProperty("webdriver.chrome.driver","webdriver\\chromedriver.exe");   //make sure this path points to the chromedriver.exe file from where this file is saved
        WebDriver driver = new ChromeDriver();
        
        String URL = "https://www.digikey.com/";

        driver.get(URL);
      
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body")));
             
     	  List<WebElement> tmp = driver.findElements(By.cssSelector("*")); 
         
         	  for (int i = 0 ; i<tmp.size() ; i++)//crashes a lot, better to find element in source from chrome developer tool or actual html source
              {
                 try
                 {
         		      if (tmp.get(i).isDisplayed() && tmp.get(i).isEnabled())
         			      System.out.print("TAG NAME: " + tmp.get(i).getTagName() + "   CLASS ATTRIBUTE: " + tmp.get(i).getAttribute("class") + "   CSS VALUE: " + tmp.get(i).getCssValue("display") + "   CLASS: "+ tmp.get(i).getClass() + "   SIZE(px): "+ tmp.get(i).getSize() + "   TEXT: " + tmp.get(i).getText() +"\n");
         	     }
                 catch (Exception e)
                 {
                     System.out.print("WHOOPS, ELEMENT DISSAPPEARED MYSTERIOUSLY");
                 }
              }
             
        System.out.print("========================================================================================================\n");
        
        if (elementExists(driver, "header-search-type")) //call elementexists()
            System.out.print("\n\nTHIS ELEMENT EXISTS");
            
        driver.quit();
	}
   
   public static boolean elementExists(WebDriver driver, String id)
   {
      boolean present;
      
      try {
      
         driver.findElement(By.id(id));   //replace to check if element exists
         present = true;
         
      }  catch (Exception e) {
      
         present = false;
      }
      
      return present;
   }
}

