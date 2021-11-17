package com.luv4code.sf.bootstrap;

import com.luv4code.sf.domain.Author;
import com.luv4code.sf.domain.Book;
import com.luv4code.sf.repositories.AuthorRepository;
import com.luv4code.sf.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author madhav = new Author("Madhav", "Ponnana");
        Book spring = new Book("Spring Framework", "12345678");
        madhav.getBooks().add(spring);
        spring.getAuthors().add(madhav);

        authorRepository.save(madhav);
        bookRepository.save(spring);

        Author rahul = new Author("Rahul", "KL");
        Book hibernate = new Book("Hibernate Framework", "342536787");
        rahul.getBooks().add(hibernate);
        hibernate.getAuthors().add(rahul);

        authorRepository.save(rahul);
        bookRepository.save(hibernate);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
    }
}
