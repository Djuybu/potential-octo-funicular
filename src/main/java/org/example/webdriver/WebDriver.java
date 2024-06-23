package org.example.webdriver;

import org.example.entity.Chapter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class WebDriver {

    public WebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    public ArrayList<String> getLinksFromParentURL(String url) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--allowed-ips=*");
        ChromeDriver driver = new ChromeDriver(options);

        ArrayList<String> links = new ArrayList<>();
        try {
            driver.get(url);

            //Move to the table of contents
            WebElement buttonBar = driver.findElement(By.className("space-x-4"));
            ArrayList<WebElement> buttons = (ArrayList<WebElement>) buttonBar.findElements(By.cssSelector("button"));
            buttons.get(3).click();

            // Locate the links
            List<WebElement> linkElements = driver.findElements(By.cssSelector("a[data-x-bind='ChapterItem(index)']"));

            for (WebElement link : linkElements) {
                System.out.println(link.getText());
                links.add(link.getAttribute("href"));
            }

            System.out.println(linkElements.size());
        } finally {
            driver.quit();
        }
        return links;
    }

    private static Chapter getChapterFromParentURL(ChromeDriver driver, String url) {
        driver.get(url);

        String title = driver.findElement(By.cssSelector("h2")).getText();

        WebElement contentBox = driver.findElement(By.id("chapter-detail"));
        String content = contentBox.findElement(By.className("break-words")).getText();

        return new Chapter(title, content);
    }

    public ArrayList<Chapter> getChaptersFromParentURL(ArrayList<String> urls) {
        int size = urls.size();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        ArrayList<Chapter> chapters = new ArrayList<>();

        int limit = Math.round(size / 4);
        ArrayList<Callable<ArrayList<Chapter>>> tasks = new ArrayList<>();
        ArrayList<Future<ArrayList<Chapter>>> futures;

        for (int i = 0; i < 4; i++) {
            int start = limit * i;
            int end = (i == 3) ? urls.size() : limit * (i + 1);
            tasks.add(new WebDriver.WebDriverCallable(urls, start, end));
        }

        try {
            futures = (ArrayList<Future<ArrayList<Chapter>>>) executor.invokeAll(tasks);
            for (Future<ArrayList<Chapter>> future : futures) {
                chapters.addAll(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        return chapters;
    }

    public static class WebDriverCallable implements Callable<ArrayList<Chapter>> {
        private ArrayList<String> links;
        private int start;
        private int end;

        public WebDriverCallable(ArrayList<String> links, int start, int end) {
            this.links = links;
            this.start = start;
            this.end = end;
        }

        public ArrayList<Chapter> getChaptersFromPartial(ArrayList<String> urls, int start, int end) {
            System.out.println("start: " + start + " end: " + end + "\n");
            ArrayList<Chapter> chapters = new ArrayList<>();
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--allowed-ips=*");
            options.addArguments();
            ChromeDriver driver = new ChromeDriver(options);
            try {
                for (int i = start; i < end; i++) {
                    Chapter c = getChapterFromParentURL(driver, urls.get(i));
                    chapters.add(c);
                }
            } finally {
                driver.quit();
            }
            return chapters;
        }

        @Override
        public ArrayList<Chapter> call() throws Exception {
            return getChaptersFromPartial(links, start, end);
        }
    }

    public void end() {
    }
}
