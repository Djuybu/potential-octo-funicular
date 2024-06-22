package org.example;
import org.example.entity.Chapter;
import org.example.entity.Book;
import org.example.publish.Publisher;
import org.example.webdriver.WebDriver;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        //initialize web driver
        WebDriver webDriver = new WebDriver();

        //get link collections
        ArrayList<String> links = webDriver.
                getLinksFromParentURL("https://metruyencv.com" +
                        "/truyen/hong-mong-chi-ton-bang-hang-the-ta-tien-de-than-phan-lo-ra-anh-sang");

        //get chapters
        ArrayList<Chapter> book_zero = webDriver.getChaptersFromParentURL(links);

        //turn of webdriver
        webDriver.end();

        //create book
        Book book_zero_book = new Book();
        book_zero_book.setChapterArrayList(book_zero);

        //publish book to .txt format
        Publisher publisher = new Publisher();
        try {
            publisher.publish(book_zero_book);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}