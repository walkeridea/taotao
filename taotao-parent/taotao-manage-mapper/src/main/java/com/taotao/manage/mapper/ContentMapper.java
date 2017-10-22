package com.taotao.manage.mapper;

import com.github.abel533.mapper.Mapper;
import com.taotao.manage.pojo.Content;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentMapper extends Mapper<Content> {
    List<Content> queryList(Long categoryId);
}
