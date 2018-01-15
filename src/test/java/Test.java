

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sdyk.ai.chromeTest;
import com.sdyk.pojo.DayNews;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Parameter;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {


    static WebDriver driver;
    final static String chromedriver_path= "chromedriver.exe";
    static List<String> addList= null;
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/testdb";
    private Dao<DayNews,Integer> DaynewsDao;

    static {
        System.setProperty("webdriver.chrome.driver",chromedriver_path);
    }
    public Test(){
        driver  = new ChromeDriver();
    }

    public void setChromeURL(String url) throws InterruptedException {
        driver.get(url);
        Thread.sleep(10000);
    }

    public String getSrc(){
        return driver.getPageSource();

    }


    public ConnectionSource Start(String DBurl) throws SQLException {
        return new JdbcConnectionSource(DBurl,"root","root");
    }

    public void setupDatabase(ConnectionSource connectionSource) throws SQLException {
        DaynewsDao = DaoManager.createDao(connectionSource,DayNews.class);
        TableUtils.createTable(connectionSource,DayNews.class);
    }

    public void writeData(DayNews dayNews) throws SQLException {
        DaynewsDao.create(dayNews);
    }


    public static void main(String[] args) throws InterruptedException, SQLException {

        chromeTest ct = new chromeTest();
        ConnectionSource sc = ct.Start(DATABASE_URL);
        ct.setupDatabase(sc);



//        DayNews dayNews = new DayNews();

        Test cDriver = new Test();
        cDriver.setChromeURL("http://www.weibo.com");

        //首页
        WebElement webElement =driver.findElement(By.className("W_input"));
        webElement.sendKeys("占豪");
        //查询页
        WebElement webElementSreach =driver.findElement(By.linkText("f"));
        webElementSreach.click();
        //个人主页
        WebElement onePage =driver.findElement(By.linkText("占豪"));
        String newURL = onePage.getAttribute("href");
//        onePage.click();
        System.out.println(newURL);

        //重新设置driver的url
        cDriver.setChromeURL(newURL);
        //博主的记录
        List<WebElement> list = driver.findElements(By.cssSelector("div.WB_cardwrap.WB_feed_type.S_bg2.WB_feed_like"));
        //分别获得每个记录
        for (WebElement ll: list
             ) {
            //定义对象
            DayNews dayNews = new DayNews();
            //图片地址
            String picSrc="";

            //文字区
            WebElement text = ll.findElement(By.className("WB_text"));
            dayNews.setNewsName(text.getText());

            //时间
            WebElement time = ll.findElement(By.className("WB_from"));
            dayNews.setSendTime(time.findElement(By.className("S_txt2")).getText());

            //图片
            List<WebElement> pics = ll.findElements(By.className("WB_pic"));
            for (WebElement pic: pics
                 ) {
                picSrc = picSrc+pic.findElement(By.tagName("img")).getAttribute("src");
            }
            dayNews.setPicUrl(picSrc);

            //转发，评论，点赞
            List<WebElement> other = ll.findElement(By.className("WB_feed_handle")).findElement(By.className("WB_handle")).findElement(By.className("WB_row_line")).findElements(By.tagName("li"));
            for (int i =1;i<other.size();i++) {

                WebElement li = other.get(i);
                List<WebElement> number= li.findElements(By.tagName("em"));

                String num = number.get(1).getText();
                if (i == 1){
                    dayNews.setForwardNum(num);
                }else if (i==2){
                    dayNews.setCommentNum(num);
                }else if (i == 3){
                    dayNews.setZanNum(num);
                }

            }

            //写入数据库
            ct.writeData(dayNews);
        }


    }


}

