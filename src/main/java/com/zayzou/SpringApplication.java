package com.zayzou;

import com.zayzou.dao.impl.AuthorDaoImpl;
import com.zayzou.dao.impl.BookDaoImpl;
import com.zayzou.domain.Author;
import com.zayzou.domain.Book;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication

public class SpringApplication /*implements ApplicationRunner*/ {


   /* AuthorDaoImpl authorDao;
    BookDaoImpl bookDao;

    public SpringApplication(AuthorDaoImpl authorDao, BookDaoImpl bookDao) {

        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }*/

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }

/*    @Override
    public void run(ApplicationArguments args) throws Exception {
        Author author = new Author(1L, "Senzu", 212);
        authorDao.create(author);
        Author auth = authorDao.findById(1L).orElseThrow();
        System.out.println(auth + " CREATED SUCCESSFULLY !");

        Book book = new Book("ISBN-09883383", "The art of war", auth.getId());
        bookDao.create(book);
        Book book1 = bookDao.findById(book.getIsbn()).orElseThrow();
        System.out.println(book1 + " CREATED SUCCESSFULLY !");


        bookDao.delete(book.getIsbn());
        System.out.println(book1 + " DELETED SUCCESSFULLY !" + bookDao.find().size() + " books found on database");

        author.setAge(24);
        authorDao.update(1L, author);
        Author auth2 = authorDao.findById(1L).orElseThrow();
        System.out.println(auth2 + " UPDATED SUCCESSFULLY !");

    }*/
}
