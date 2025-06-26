package com.ncwu.dao;

import com.ncwu.domain.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: NoticeDao
 * Package: com.ncwu.dao
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/7 8:52
 * @Version 1.0
 */
@Mapper
public interface NoticeDao {
    @Select("select * from tb_notice")
    @Results({
            @Result(property = "noticeId", column = "notice_id"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "outTime", column = "out_time"),
            @Result(property = "status", column = "notice_status")
    })
    public List<Notice> getAll();

    @Results({
            @Result(property = "noticeId", column = "notice_id"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "outTime", column = "out_time"),
            @Result(property = "status", column = "notice_status")
    })
    @Select("select * from tb_notice where notice_id = #{id}")
    public Notice getById(@Param("id") int id);

    @Results({
            @Result(property = "noticeId", column = "notice_id"),
            @Result(property = "publishTime", column = "publish_time"),
            @Result(property = "outTime", column = "out_time"),
            @Result(property = "status", column = "notice_status")
    })
    @Select("select * from tb_notice where notice_status = #{status}")
    public List<Notice> getByStatus(@Param("status") String status);

    @Update("update tb_notice set title = #{title}, content = #{content}, out_time = #{outTime} ,notice_status = #{status}" +
            " where notice_id = #{noticeId}")
    public int update(Notice notice);

    @Delete("delete from tb_notice where notice_id = #{id}")
    public int delete(@Param("id") int id);

    @Insert("insert into tb_notice (title,content,publish_time,out_time,notice_status) " +
            "values(#{title},#{content},now(),#{outTime},#{status})")
    public int add(Notice notice);
}
