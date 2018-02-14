package Utility;

import org.openqa.selenium.By;

public interface ObjectIds {

    //Object id's for Dimetis POC
    //All the xpaths for identifying objects/items on browser.
    By UserName_LMO= By.xpath("//*[@id=\"isc_12\"]");       //LMO Username
    By Password_LMO= By.xpath("//*[@id=\"isc_15\"]");       //LMO Password
    By Role_LMO = By.xpath("//*[@id=\"isc_1A\"]");          //LMO Role
    By Lgn_btn_LMO = By.xpath("//*[@id=\"isc_1J\"]/table/tbody/tr/td");
    By Easy_conn_LMO = By.xpath("//*[@id=\"isc_3L\"]");
    By Lgo_btn_LMO = By.id("isc_2J");                       //LMO Logout button

    //Object id's for Aperi POC
    By UserName_Aperi= By.xpath("//*[@id=\"root\"]/div/div/form/div[1]/input");
    By Password_Aperi=By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/input");
    By Lgn_Btn_Aperi=By.xpath("//*[@id=\"root\"]/div/div/form/button");
    By Create_Flow_Aperi=By.xpath("//*[@id=\"scrl\"]/div[2]/div[3]/div[2]/button[2]");
    By Front_Intf_Aperi=By.xpath("/html/body/div[1]/div/div/div/div/form/div[2]/div[1]/div/div[1]");
    By Front_Intf_xe3_Aperi=By.xpath("/html/body/div[1]/div/div/div/div/form/div[2]/div[1]/div/div[2]/div/div/div[3]/div");
    By Src_ip_dest_Aperi=By.xpath("/html/body/div[1]/div/div/div/div/form/div[3]/div[2]/input");
    By Cancel_Btn_Aperi=By.xpath("/html/body/div[1]/div/div/div/div/form/div[11]/div[2]/div/button[1]");
}
