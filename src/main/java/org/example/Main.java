package org.example;
import org.example.publish.Chapter;
import org.example.webdriver.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        WebDriver webDriver = new WebDriver();
        ArrayList<String> links = webDriver.
                getLinksFromParentURL("https://metruyencv.com/truyen/" +
                        "cau-tai-vu-su-the-gioi-trong-truong-sinh-thu");

        Chapter chapter_one = webDriver.getChapterFromParentURL(links.get(0));
    }
}