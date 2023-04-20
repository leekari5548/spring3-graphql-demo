package cn.leekari.dao;

import cn.leekari.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;

@Mapper
public interface UserMapper {


    @Select({
            "select * from user where id = #{id}"
    })
    User selectById(Long id);


    @Select({
            "<script>",
            "select * from user where id in",
            "(<foreach collection='ids' item='id' separator=','>",
            "#{id}",
            "</foreach>)",
            "</script>",
    })
    List<User> selectByIds(@Param("ids") Collection<Long> ids);
}
