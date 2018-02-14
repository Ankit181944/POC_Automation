package Utility;

import Telstra.StepImplementation;
import Utility.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Collections;
import org.apache.commons.io.FileUtils;


public class Browser {
    public static int SCREENSHOT_NUMBER = 1;
    public static WebDriver Initiate_New_Browser(String Browser) {
        WebDriver driver = null;
        //System.out.println("Outside");
        if (Browser.toLowerCase().contains("firefox")) {
            //System.out.println("Inside");
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("app.update.auto", false);
            profile.setPreference("app.update.enabled", false);
            driver = new FirefoxDriver(profile);
            return driver;
        } else if (Browser.toLowerCase().contains("internet")) {
            driver = new InternetExplorerDriver();
            return driver;
        } else if (Browser.toLowerCase().contains("chrome")) {
            driver = new ChromeDriver();
            return driver;
        } else if (Browser.toLowerCase().contains("headless")) {
            DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
            String[] phantomArgs = new String[]{
                    "--webdriver-loglevel=NONE"
            };
            capabilities.setCapability("phantomjs.cli.args", Collections.singletonList("--ignore--ssl-errors=yes"));
            capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);
            driver = new PhantomJSDriver(capabilities);
            return driver;
        }
        return driver;
    }

    public static String getProperty(String property){
        if(property.contains("path")){
            return System.getProperty("user.dir");
        }
        return "Invalid Property Name";
    }
    public static void TakeScreenshot(String Testcase_name){
        try{
            String currentEnvironment = "DemoEnv";
            File srcFile = ((TakesScreenshot) StepImplementation.getDriver()).getScreenshotAs(OutputType.FILE);
            String currentDir = getProperty("path")+("\\out\\test\\Automation Framework Gauge");
            String file_name = currentDir+"\\"+Testcase_name+"\\"+Testcase_name+"_"+currentEnvironment+"_"+SCREENSHOT_NUMBER+".png";
            System.out.println(file_name);
            FileUtils.copyFile(srcFile,new File(file_name));
            SCREENSHOT_NUMBER++;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
