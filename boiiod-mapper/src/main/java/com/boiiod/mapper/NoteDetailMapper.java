package com.boiiod.mapper;

import com.boiiod.entity.NoteDetail;
import com.boiiod.entity.NoteDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoteDetailMapper {
    int countByExample(NoteDetailExample example);

    int deleteByExample(NoteDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NoteDetail record);

    int insertSelective(NoteDetail record);

    List<NoteDetail> selectByExample(NoteDetailExample example);

    NoteDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NoteDetail record, @Param("example") NoteDetailExample example);

    int updateByExample(@Param("record") NoteDetail record, @Param("example") NoteDetailExample example);

    int updateByPrimaryKeySelective(NoteDetail record);

    int updateByPrimaryKey(NoteDetail record);
}