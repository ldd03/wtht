package com.ncwu.dao;

import com.ncwu.domain.Place;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: PlaceDao
 * Package: com.ncwu.dao
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/8 8:45
 * @Version 1.0
 */
@Mapper
public interface PlaceDao {
    @Select("select * from tb_place order by facility COLLATE utf8mb4_general_ci")
    @Results({
            @Result(property = "placeId", column = "place_id"),
            @Result(property = "status", column = "place_status")
    })
    public List<Place> getAll();


    @Results({
            @Result(property = "placeId", column = "place_id"),
            @Result(property = "status", column = "place_status")
    })
    @Select("select * from tb_place where place_id = #{id}")
    public Place getById(@Param("id") int id);

    @Results({
            @Result(property = "placeId", column = "place_id"),
            @Result(property = "status", column = "place_status")
    })
    @Select("select * from tb_place where name = #{name}")
    public List<Place> getByName(@Param("name") String name);


    @Update("update tb_place set name = #{name}, site = #{site}, facility = #{facility} , description = #{description}, place_status = #{status}" +
            " where place_id = #{placeId}")
    public int update(Place place);

    @Delete("delete from tb_place where place_id = #{id}")
    public int delete(@Param("id") int id);

    @Insert("insert into tb_place (name,site,facility,description,place_status) " +
            "values(#{name},#{site},#{facility},#{description},#{status})")
    public int add(Place place);
}
