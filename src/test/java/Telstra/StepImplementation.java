package Telstra;

import com.thoughtworks.gauge.Step;

import Utility.Log;
import javafx.beans.binding.ObjectExpression;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import Utility.Browser;
import static org.junit.Assert.assertEquals;
import Utility.ObjectIds;

public class StepImplementation {

    public static WebDriver driver = null;
    public String WindowHandletonavigate="";
    public String TC_name_POC = "NE57_Aperi_App_UI_POC";
    public void InitateLogger() {
        // Provide Log4j configuration settings
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("Dementis_POC_TC01");
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public void InitateLogger_Aperi() {
        // Provide Log4j configuration settings
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase(TC_name_POC);
    }

    public void InitiateDriver(String Driver) {
        driver = Browser.Initiate_New_Browser(Driver);
        Log.info("New Firefox driver instantiated");
    }

    public void setWindowHandle(String windowHandle){
        WindowHandletonavigate=windowHandle;
    }
    @Step("Launch Dimetis Application with Browser as <browser>")
    public void Launch_Dimetis(String browser) {
        try {
            //Initiate Logger
            InitateLogger();
            //Initiate Driver
            InitiateDriver(browser);

            Log.info("Explicit wait applied on the driver for 10 seconds");
            getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Log.info("Implicit wait applied on the driver for 10 seconds");

            //Open the maximized web Browser for link Manager
            getDriver().manage().window().maximize();
            Log.info("Browser window maximized to full resolution");
            getDriver().get("http://10.183.111.74/linkmanager/LinkManager.html");
            Log.info("Opening Demitis URL");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Step("Login application with username as <u_name> and password <pass> and role <role>")
    public void Dimetis_POC(String u_name, String pass, String role) {
        try {
            //Identify Username and enter its value
            WebElement username = getDriver().findElement(ObjectIds.UserName_LMO);
            username.sendKeys(u_name);
            Log.info("User Name successfully entered.");

            //Identify Password and enter its value
            WebElement password = getDriver().findElement(ObjectIds.Password_LMO);
            password.sendKeys(pass);
            Log.info("Password successfully entered.");

            //Navigate to Role as Provisioner.
            WebElement Role_selector = getDriver().findElement(ObjectIds.Role_LMO);
            Role_selector.sendKeys(Keys.ARROW_DOWN);
            Role_selector.sendKeys(Keys.ARROW_DOWN);
            Log.info("Successfully selected Role as " + role);

            //Click on Login button
            WebElement Ln_button = getDriver().findElement(ObjectIds.Lgn_btn_LMO);
            Ln_button.click();
            Log.info("Successfully Logged in Demitis Application.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Step("Navigate to Easy Connector")
    public void Easy_Connector() {
        //Click on Easy Connector Tab
        WebElement Easy_connector = getDriver().findElement(ObjectIds.Easy_conn_LMO);
        Easy_connector.click();
        Log.info("Clicked on Easy Connector Navigation Tab.");

        /*//Navigate to select service - HD/SD
             WebElement Service_bar=driver.findElement(By.xpath("//*[@name=\"isc_2Z\"]"));
             Service_bar.click();*/
    }

    @Step("Logout the application")
    public void Logout() {
        try {
            //Logout the application
            Thread.sleep(3000);
            WebDriverWait wait = new WebDriverWait(getDriver(), 10);
            WebElement LogOut = wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectIds.Lgo_btn_LMO));
            LogOut.click();
            Log.info("Click action is performed on Log Out link.");
            Log.info("LogOut of the application successfull.");

            //Close the driver
            getDriver().close();
            Log.info("Browser closed successfully.");
            Log.endTestCase("Dementis_POC_TC01");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Step("Launch Aperi Application with Browser as <firefox>")
    public void Launch_Aperi(String browser) {

        try {
            //Initiate Logger
            InitateLogger_Aperi();
            //Initiate Driver
            InitiateDriver(browser);

            Log.info("Explicit wait applied on the driver for 10 seconds");
            getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            //Open the maximized web Browser for link Manager
            getDriver().manage().window().maximize();
            Log.info("Browser window maximized to full resolution");
            //Open Aperi Chassis
            getDriver().get("https://192.168.104.68/#/login");
            Log.info("Aperi Chassis opened successfully to Login to Chassis.");

        } catch (Exception e) {
            Log.info("Error:-" + e.toString());
        }

    }

    @Step("Login application with username as <admin> and password <admin>")
    public void Login_Aperi(String userName, String passWord) {
        try {

            //Enter UserName and Password to login to Aperi Chassis
            WebElement username = getDriver().findElement(ObjectIds.UserName_Aperi);
            //Take Screenshot
            Browser.TakeScreenshot(TC_name_POC);
            username.sendKeys(userName);
            Log.info("User Name Successfully Entered on the Login Page");

            WebElement password = getDriver().findElement(ObjectIds.Password_Aperi);
            password.sendKeys(passWord);
            Log.info("Password Successfully Entered on the Login Page");

            //Click on the Login btn
            WebElement login_btn = getDriver().findElement(ObjectIds.Lgn_Btn_Aperi);
            //Take Screenshot
            Browser.TakeScreenshot(TC_name_POC);
            login_btn.click();
            Log.info("Successfully logged into Aperi Chassis");

            Thread.sleep(2000);
        } catch (Exception e) {
            Log.info("Error:-" + e.toString());
        }
    }

    @Step("Navigate and open Slot number <4> where latest App is loaded")
    public void Slot_selection_Chassis(String Slot_number) {
        //Get List of all webelements on the page as the page is Dynamically changing
        List<WebElement> All_el = getDriver().findElements(By.xpath("//*"));
        Log.info("********Get Dynamic List of all web-elements for slots selection as Page is Dynamically changing");
        //System.out.println(All_el.size());
        int i = 0;
        for (WebElement e : All_el) {
            String webEleText = e.getText();
            System.out.println("Traversing Element No:-" + (i + 1) + "/.....");
            if (Slot_number.contains("1") || Slot_number.contains("2") || Slot_number.contains("3") || Slot_number.contains("4") || Slot_number.contains("5")) {
                if (webEleText.length() <= 18) {
                    if (webEleText.length() > 3) {
                        if (webEleText.substring(0, 1).contains(Slot_number) && webEleText.substring(1, 2).contains(" ")) {
                            i = i + 3;
                            //Take Screenshot
                            Browser.TakeScreenshot(TC_name_POC);
                            All_el.get(i).click();
                            Log.info("Clicked Successfully on the Slot No:" + Slot_number);
                            break;
                        }
                    }
                }
            } else {
                Log.info("Invalid Slot Number");
                break;
            }
            i++;
        }

    }

    @Step("Launch Batch file to Navigate Windows popup for Authentication")
    public void Window_Authentication_nav() {
        try {
            Thread.sleep(2000);
            Runtime.getRuntime().exec("C:\\Users\\Engineer\\Desktop\\Automation Framework Gauge\\src\\test\\java\\Utility\\HandleAuthentication.exe");
            Log.info("Successfully ran Batch file to enter Credentials on Authentication Window Pop Up");
            Thread.sleep(1000);
        } catch (Exception e) {
            Log.info("Error:-" + e.toString());
        }
    }

    @Step("Navigate to Create New Stream Flow for the Aperi App loaded")
    public void Create_Stream_Flow() {
        try {
            String winHandleBefore = getDriver().getWindowHandle();
            setWindowHandle(winHandleBefore);
            //Switch to new Window
            for (String winHandle : getDriver().getWindowHandles()) {
                getDriver().switchTo().window(winHandle);
            }
            //Perform Operations on App window
            //Take Screenshot
            Browser.TakeScreenshot(TC_name_POC);
            //Add new Stream
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(getDriver(), 10);
            WebElement Create_flow = wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectIds.Create_Flow_Aperi));
            //Take Screenshot
            Browser.TakeScreenshot(TC_name_POC);
            Create_flow.click();
            Log.info("Clicked on Created Stream Button Successfully");
        } catch (Exception e) {
            Log.info("Error:-" + e.toString());
        }
    }

    @Step("Select value of front interface and Source IP for destination as <10.12.123.55> for the new Create Stream flow")
    public void Values_Create_Stream(String ip_dest) {
        try {
            //Select front interface xe3 for Create Stream
            WebElement frontInterface = getDriver().findElement(ObjectIds.Front_Intf_Aperi);
            //Take Screenshot
            Browser.TakeScreenshot(TC_name_POC);
            frontInterface.click();
            WebElement xe3_option = getDriver().findElement(ObjectIds.Front_Intf_xe3_Aperi);
            xe3_option.click();
            Thread.sleep(1000);
            Log.info("Selected front interface cage as xe3");

            //Enter source IP for destination
            WebElement sourceIp = getDriver().findElement(ObjectIds.Src_ip_dest_Aperi);
            sourceIp.sendKeys(ip_dest);
            Log.info("Successfully entered Source IP for destination in the Text box");
            //Wait to explain in POC before clicking on cancel button
            //Take Screenshot
            Browser.TakeScreenshot(TC_name_POC);
            Thread.sleep(2000);
        } catch (Exception e) {
            Log.info("Error:-" + e.toString());
        }
    }

    @Step("Click Cancel to navigate out of the Create Stream Flow")
    public void Cancel_Create_Stream() {
        //Click on cancel
        WebElement Cancel_btn = getDriver().findElement(ObjectIds.Cancel_Btn_Aperi);
        Cancel_btn.click();
        Log.info("Clicked on cancel button to successfully cancel the Flow Stream Creation");
        //Take Screenshot
        Browser.TakeScreenshot(TC_name_POC);
    }

    @Step("Logout the App and Chassis")
    public void Logout_Aperi() {
        //Close the window after testing done on App
        getDriver().close();
        Log.info("Successfully closed the App window");

        //Switch back to the original window
        getDriver().switchTo().window(WindowHandletonavigate);
        //Take Screenshot
        Browser.TakeScreenshot(TC_name_POC);
        //Close the main App for Aperi Chassis
        getDriver().close();
        Log.info("Successfully closed the Chassis window for Aperi");
    }
}