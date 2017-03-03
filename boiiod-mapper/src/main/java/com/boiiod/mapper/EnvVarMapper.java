package com.boiiod.mapper;

import com.boiiod.entity.EnvVar;
import com.boiiod.entity.EnvVarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnvVarMapper {
    int countByExample(EnvVarExample example);

    int deleteByExample(EnvVarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EnvVar record);

    int insertSelective(EnvVar record);

    List<EnvVar> selectByExample(EnvVarExample example);

    EnvVar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EnvVar record, @Param("example") EnvVarExample example);

    int updateByExample(@Param("record") EnvVar record, @Param("example") EnvVarExample example);

    int updateByPrimaryKeySelective(EnvVar record);

    int updateByPrimaryKey(EnvVar record);
}