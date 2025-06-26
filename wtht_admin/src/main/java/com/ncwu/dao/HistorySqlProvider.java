package com.ncwu.dao;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

public class HistorySqlProvider {
    public String filterHistory(final Map<String, Object> params) {
        return new SQL(){
            {
                SELECT("h.*, u.name as user_name, u.role, p.name as place_name, p.site as place_site, t.time_range");
                FROM("tb_history h");
                LEFT_OUTER_JOIN("tb_user u on h.user_id = u.user_id");
                LEFT_OUTER_JOIN("tb_place p on h.place_id = p.place_id");
                LEFT_OUTER_JOIN("tb_avaliabletime t on h.time_id = t.time_id");
                WHERE("1=1");
                if(params.get("role") != null && !params.get("role").toString().isEmpty()) {
                    WHERE("u.role = #{role}");
                }
                if(params.get("placeName") != null && !params.get("placeName").toString().isEmpty()) {
                    WHERE("p.name = #{placeName}");
                }
                if(params.get("userName") != null && !params.get("userName").toString().isEmpty()) {
                    WHERE("u.name LIKE CONCAT('%', #{userName}, '%')");
                }
                if(params.get("date") != null && !params.get("date").toString().isEmpty()) {
                    WHERE("DATE(h.create_time) = #{date}");
                }
                if(params.get("timeRange") != null && !params.get("timeRange").toString().isEmpty()) {
                    WHERE("t.time_range = #{timeRange}");
                }
            }
        }.toString();
    }
} 