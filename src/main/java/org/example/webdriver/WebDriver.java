package org.example.webdriver;

import org.example.entity.Chapter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;


public class WebDriver {
    public ChromeDriver driver;
    public WebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--allowed-ips=*");
        driver = new ChromeDriver(options);
    }

    public ArrayList<String> getLinksFromParentURL(String url) {
        ArrayList<String> links = new ArrayList<>();
        driver.get(url);

        //Move to the table of contents
        WebElement buttonBar = driver.findElement(By.className("space-x-4"));
        ArrayList<WebElement> buttons = (ArrayList<WebElement>) buttonBar.findElements(By.cssSelector("button"));
        buttons.get(3).click();

//      Locate the links
        List<WebElement> linkElements = driver.findElements(By.cssSelector("a[data-x-bind='ChapterItem(index)']"));

        for (WebElement link : linkElements) {
            System.out.println(link.getText());
            links.add(link.getAttribute("href"));
        }

        System.out.println(linkElements.size());
        return links;
    }

    private Chapter getChapterFromParentURL(String url) {
        driver.get(url);

        String title = driver.findElement(By.cssSelector("h2")).getText();

        WebElement contentBox = driver.findElement(By.id("chapter-detail"));
        String content = contentBox.findElement(By.className("break-words")).getText();

        Chapter chapter = new Chapter(title, content);
        System.out.println(chapter.getTitle() + "\n" + chapter.getContent());

        return chapter;
    }

    public ArrayList<Chapter> getChaptersFromParentURL(ArrayList<String> urls) {
        ArrayList<Chapter> chapters = new ArrayList<>();
        for (String url : urls) {
            chapters.add(getChapterFromParentURL(url));
        }
        return chapters;
    }

    public void end() {
        driver.quit();
    }
}
