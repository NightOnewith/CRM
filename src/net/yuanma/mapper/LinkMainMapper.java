package net.yuanma.mapper;

import java.util.List;
import net.yuanma.model.LinkMain;
import net.yuanma.model.LinkMainExample;
import org.apache.ibatis.annotations.Param;

public interface LinkMainMapper {
    int countByExample(LinkMainExample example);

    int deleteByExample(LinkMainExample example);

    int deleteByPrimaryKey(Integer linkMainId);

    int insert(LinkMain record);

    int insertSelective(LinkMain record);

    List<LinkMain> selectByExample(LinkMainExample example);

    LinkMain selectByPrimaryKey(Integer linkMainId);

    int updateByExampleSelective(@Param("record") LinkMain record, @Param("example") LinkMainExample example);

    int updateByExample(@Param("record") LinkMain record, @Param("example") LinkMainExample example);

    int updateByPrimaryKeySelective(LinkMain record);

    int updateByPrimaryKey(LinkMain record);
    //查询所有
    List<LinkMain> findAll(LinkMain linkMain);
    //根据id查询
    LinkMain selectById(int id);
}