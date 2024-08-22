package org.ssischoolbackend.dao;

import org.springframework.jdbc.core.RowMapper;
import org.ssischoolbackend.model.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RoomRowMapper implements RowMapper<Room> {

    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = new Room();
        room.setId(rs.getLong("id"));
        room.setName(rs.getString("name"));
        room.setCapacity(rs.getInt("capacity"));
        String materielIdsStr = rs.getString("materielIds");
        if (materielIdsStr != null) {
            List<Integer> materielIds = Arrays.stream(materielIdsStr.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            room.setMaterielIds(materielIds);
        }
        return room;
    }
}
