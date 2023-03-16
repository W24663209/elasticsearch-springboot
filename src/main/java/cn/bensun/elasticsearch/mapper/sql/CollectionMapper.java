package cn.bensun.elasticsearch.mapper.sql;

import cn.bensun.elasticsearch.domain.Collection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionMapper {

    Double test();

    /**
     * @Description
     * @author weizongtang
     * @CreateTime 2023/03/16 14:20:10
     */
    List<Collection> list(Collection collection);
}
