package cn.leekari.service;

import cn.leekari.dao.UserMapper;
import cn.leekari.entity.Book;
import cn.leekari.entity.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User searchUserById(Long id) {
        System.err.println(id);
        return userMapper.selectById(id);
    }


    public Mono<Map<Book, User>> searchUserById(List<Book> books) {
        System.err.println(books);
        List<Long> authorIds = books.stream().map(Book::getAuthorId).collect(Collectors.toList());
        if (authorIds.isEmpty()) {
            return Mono.empty();
        }
        List<User> users = userMapper.selectByIds(authorIds);
        Map<Book, User> bookUserMap = new HashMap<>();
        books.forEach(book -> {
            users.forEach(user -> {
                if (user.getId().equals(book.getAuthorId())) {
                    bookUserMap.put(book, user);
                }
            });
        });
        return Mono.just(bookUserMap);
    }
}
