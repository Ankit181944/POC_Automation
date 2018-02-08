package Utility;

import Utility.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collection;
import java.util.Collections;

public class Browser {

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
}
