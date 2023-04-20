package cn.leekari.controller;

import cn.leekari.entity.Book;
import cn.leekari.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public Book findBook(@Argument Long id) {
        return bookService.searchById(id);
    }


    @QueryMapping
    public List<Book> books(@Argument Integer page, @Argument Integer offset) {
        return bookService.books(page, offset);
    }

    @MutationMapping
    public Book addBook(@Argument String name,@Argument Long authorId,@Argument String publishTime) {
        return bookService.addBook(name, authorId, publishTime);
    }
}
