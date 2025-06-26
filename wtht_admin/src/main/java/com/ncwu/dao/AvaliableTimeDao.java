package com.ncwu.dao;

import com.ncwu.domain.AvaliableTime;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: AvaliableTimeDao
 * Package: com.ncwu.dao
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/8 21:42
 * @Version 1.0
 */
@Mapper
public interface AvaliableTimeDao {

    @Select("select * from tb_avaliableTime")
    @Results({
            @Result(property = "timeId", column = "time_id"),
            @Result(property = "timeRange", column = "time_range")
    })
    public List<AvaliableTime> getAll();


    @Select("select * from tb_avaliableTime where time_id = #{id}")
    @Results({
            @Result(property = "timeId", column = "time_id"),
            @Result(property = "timeRange", column = "time_range")
    })
    public AvaliableTime getById(@Param("id") Integer id);
}
