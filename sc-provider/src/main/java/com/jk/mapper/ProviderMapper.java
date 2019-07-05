package com.jk.mapper;

import com.jk.model.News;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ProviderMapper {
    @Select("select count(*) from 71news")
    Integer querynewstotal();
    @Select("select * from 71news limit #{start},#{pageSize}")
    List<News> querynewsrows(@Param("start") Integer start,
                             @Param("pageSize") Integer pageSize);
    @Delete("delete from 71news where id =#{id}")
    boolean deletenews(@Param("id") Integer id);

    boolean addnews(News news);
    @Select("select * from 71news where id=#{id}")
    News selectnewsbyid(Integer id);

    boolean updatenews(News news);
}
