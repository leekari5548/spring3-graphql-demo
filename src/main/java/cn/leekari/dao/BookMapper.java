package cn.leekari.dao;


import cn.leekari.entity.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {


    @Select({
            "select * from book where id = #{id}"
    })
    Book findById(Long id);


    @Select({
            "<script>",
            "select * from book ",
            "<if test='offset != null and page != null'>",
            "limit #{page},#{offset}",
            "</if>",
            "</script>"
    })
    List<Book> findAll(@Param("page") Integer page, @Param("offset") Integer offset);


    @Insert({
            "insert into book(id,name,authorId,publishTime) values(#{id},#{name},#{authorId},#{publishTime})"
    })
    int insert(Book book);
}
