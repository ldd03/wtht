package com.ncwu.dao;

import com.ncwu.domain.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * ClassName: HistoryDao
 * Package: com.ncwu.dao
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/8 21:23
 * @Version 1.0
 */
@Mapper
public interface HistoryDao {
    @Select("select * from tb_history")
    @Results({
            @Result(property = "historyId", column = "history_id"),
            @Result(property = "user.userId", column = "user_id"),
            @Result(property = "place.placeId", column = "place_id"),
            @Result(property = "avaliableTime.timeId", column = "time_id"),
            @Result(property = "createTime", column = "create_time")
    })
    public List<History> getAll();

    @SelectProvider(type = com.ncwu.dao.HistorySqlProvider.class, method = "filterHistory")
    @Results({
        @Result(property = "historyId", column = "history_id"),
        @Result(property = "user.userId", column = "user_id"),
        @Result(property = "user.name", column = "user_name"),
        @Result(property = "user.role", column = "role"),
        @Result(property = "place.placeId", column = "place_id"),
        @Result(property = "place.name", column = "place_name"),
        @Result(property = "place.site", column = "place_site"),
        @Result(property = "avaliableTime.timeId", column = "time_id"),
        @Result(property = "avaliableTime.timeRange", column = "time_range"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "status", column = "status"),
        @Result(property = "remark", column = "remark")
    })
    List<History> filterHistory(@Param("role") String role, @Param("placeName") String placeName, @Param("userName") String userName, @Param("date") String date, @Param("timeRange") String timeRange);

    @Insert("insert into tb_history(user_id, place_id, time_id, status, remark) values(#{user.userId}, #{place.placeId}, #{avaliableTime.timeId}, #{status}, #{remark})")
    int addHistory(History history);

    @Select("select * from tb_history where place_id = #{placeId} and time_id = #{timeId} and status = '已预约'")
    @Results({
        @Result(property = "historyId", column = "history_id"),
        @Result(property = "user.userId", column = "user_id"),
        @Result(property = "place.placeId", column = "place_id"),
        @Result(property = "avaliableTime.timeId", column = "time_id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "status", column = "status"),
        @Result(property = "remark", column = "remark")
    })
    List<History> getByPlaceAndTime(@Param("placeId") int placeId, @Param("timeId") int timeId);
}
