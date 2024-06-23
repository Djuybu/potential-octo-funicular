package org.example.publish;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.example.entity.Book;
import org.example.entity.Chapter;

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
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(book.getName() + "\n");
        bufferedWriter.write(book.getAuthor() + "\n");
        ArrayList<Chapter> chapters = book.getChapterArrayList();
        for(Chapter chapter : chapters) {
            bufferedWriter.write(chapter.getTitle() + "\n");
            bufferedWriter.write(chapter.getContent() + "\n");
        }
        bufferedWriter.close();
    }
}
