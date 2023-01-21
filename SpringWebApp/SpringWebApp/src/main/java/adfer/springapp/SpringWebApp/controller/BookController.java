package adfer.springapp.SpringWebApp.controller;

import adfer.springapp.SpringWebApp.model.Book;
import adfer.springapp.SpringWebApp.repositories.AuthorRepository;
import adfer.springapp.SpringWebApp.repositories.BookRepository;
import adfer.springapp.SpringWebApp.repositories.PublisherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/books")
    public String homeBooks(){
        return "books/books";
    }

    @GetMapping("/books/list")
    public String getBooks(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "books/list";
    }

    @GetMapping (value = "/books/addBook")
    public String addBook(Model model){
        model.addAttribute("newBook", new Book());
        model.addAttribute("authorList", authorRepository.findAll());
        model.addAttribute("publisherList", publisherRepository.findAll());
        return "books/newBookForm";
    }

    @PostMapping (value = "/books/save")
    public String saveBook(Book book){
        bookRepository.save(book);
        return "redirect:/books/list";
    }

}
