package net.yuanma.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.sun.org.glassfish.gmbal.ParameterNames;

import net.yuanma.model.LogIn;
import net.yuanma.model.LogInExample;

public interface LogInMapper {
    int countByExample(LogInExample example);

    int deleteByExample(LogInExample example);

    int deleteByPrimaryKey(Integer userId);

    //添加账户
    int insert(LogIn record);

    int insertSelective(LogIn record);

    List<LogIn> selectByExample(LogInExample example);

    LogIn selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") LogIn record, @Param("example") LogInExample example);

    int updateByExample(@Param("record") LogIn record, @Param("example") LogInExample example);

    int updateByPrimaryKeySelective(LogIn record);

    int updateByPrimaryKey(LogIn record);
    
    //通过用户登录的信息查询
    LogIn selectByLogIn(@Param("id")int id,@Param("password")String password);
    
    //查询log_in表中所有数据
    List<LogIn> findAll();
    
}