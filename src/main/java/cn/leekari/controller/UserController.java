package cn.leekari.controller;

import cn.leekari.entity.Book;
import cn.leekari.entity.User;
import cn.leekari.service.UserService;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public User user(@Argument(value = "id") Long userId, @ContextValue(value = "user_info") User userInfo) {
        System.err.println(userInfo);
        return userService.searchUserById(userId);
    }

//    @SchemaMapping
//    public User author(Book book) {
//        return userService.searchUserById(book.getAuthorId());
//    }

    @BatchMapping(typeName = "Book", field = "author")
    public Mono<Map<Book, User>> authors(List<Book> books) {
        return userService.searchUserById(books);
    }
}
