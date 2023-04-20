package cn.leekari.service;

import cn.leekari.dao.BookMapper;
import cn.leekari.entity.Book;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BookService {

    @Resource
    private BookMapper bookMapper;

    public Book searchById(Long id) {
        return bookMapper.findById(id);
    }

    public List<Book> books(Integer page, Integer offset) {
        return bookMapper.findAll(page, offset);
    }

    public Book addBook(String name, Long authorId, String publishTimeStr) {
        Book book = new Book();
        Long id = System.currentTimeMillis();
        book.setId(id);
        book.setName(name);
        book.setAuthorId(authorId);
        LocalDate publishTime = LocalDate.parse(publishTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        book.setPublishTime(publishTime);
        int insert = bookMapper.insert(book);
        if (insert == 1) {
            return bookMapper.findById(id);
        }else {
            return new Book();
        }
    }
}
