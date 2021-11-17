package com.luv4code.sf.bootstrap;

import com.luv4code.sf.domain.Author;
import com.luv4code.sf.domain.Book;
import com.luv4code.sf.domain.Publisher;
import com.luv4code.sf.repositories.AuthorRepository;
import com.luv4code.sf.repositories.BookRepository;
import com.luv4code.sf.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("Luv4code");
        publisher.setCity("Hyderabad");
        publisher.setState("TS");

        publisherRepository.save(publisher);
        System.out.println("Publisher count: " + publisherRepository.count());

        Author madhav = new Author("Madhav", "Ponnana");
        Book spring = new Book("Spring Framework", "12345678");
        madhav.getBooks().add(spring);
        spring.getAuthors().add(madhav);

        spring.setPublisher(publisher);
        publisher.getBooks().add(spring);

        authorRepository.save(madhav);
        bookRepository.save(spring);
        publisherRepository.save(publisher);

        Author rahul = new Author("Rahul", "KL");
        Book hibernate = new Book("Hibernate Framework", "342536787");
        rahul.getBooks().add(hibernate);
        hibernate.getAuthors().add(rahul);
        hibernate.setPublisher(publisher);
        publisher.getBooks().add(hibernate);

        authorRepository.save(rahul);
        bookRepository.save(hibernate);
        publisherRepository.save(publisher);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher No Of Books: " + publisher.getBooks().size());
    }
}
