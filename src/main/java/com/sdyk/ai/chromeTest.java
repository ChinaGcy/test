package com.sdyk.ai;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class chromeTest {
    static WebDriver driver;
    final static String chromedriver_path= "chromedriver.exe";

    static {
        System.setProperty("webdriver.chrome.driver",chromedriver_path);
    }
    public chromeTest(){
        driver  = new ChromeDriver();
    }

    public void setChromeURL(String url) throws InterruptedException {
        driver.get(url);
        Thread.sleep(5000);
    }

    public String getSrc(){
        return driver.getPageSource();
    }

    public static void main(String[] args) throws InterruptedException {

        chromeTest cDriver = new chromeTest();
        cDriver.setChromeURL("http://www.weibo.com");

        String src = cDriver.getSrc();
        System.out.println(src);
    }


}
