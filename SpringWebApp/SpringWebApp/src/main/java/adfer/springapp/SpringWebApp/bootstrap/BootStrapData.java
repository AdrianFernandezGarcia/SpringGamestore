package adfer.springapp.SpringWebApp.bootstrap;

import adfer.springapp.SpringWebApp.model.Author;
import adfer.springapp.SpringWebApp.model.Book;
import adfer.springapp.SpringWebApp.model.Publisher;
import adfer.springapp.SpringWebApp.repositories.AuthorRepository;
import adfer.springapp.SpringWebApp.repositories.BookRepository;
import adfer.springapp.SpringWebApp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author author = new Author("George","Orwell");
        Book book = new Book("1986","123123");
        author.getBooks().add(book);
        book.getAuthors().add(author);

        authorRepository.save(author);
        bookRepository.save(book);

        Author author2 = new Author("Brandon","Sanderson");
        Book book2 = new Book("Mistborn","234234");
        author2.getBooks().add(book2);
        book2.getAuthors().add(author2);

        authorRepository.save(author2);
        bookRepository.save(book2);

        Publisher publisher = new Publisher("Nova","Calle Alcalá","Madrid","Spain",28806);
        publisher.getBooks_publisher().add(book);
        publisher.getBooks_publisher().add(book2);
        publisherRepository.save(publisher);

        System.out.println("Number of books: "+bookRepository.count());
        System.out.println("Number of publishers: "+publisherRepository.count());
    }
}
