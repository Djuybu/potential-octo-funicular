package org.example.publish;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.example.entity.Book;

public class Publisher {
    String path = System.getProperty("user.dir") + File.separator + "books";
    public void publish(Book book) throws IOException {
        System.out.println(path);
        File file = new File(path + File.separator + book.getName() + ".txt");
        if(file.createNewFile()) {
            System.out.println("File created: " + file.getAbsolutePath());
        } else {
            System.out.println("File already exists: " + file.getAbsolutePath());
        }
    }
}
