package org.ssischoolbackend.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.ssischoolbackend.model.Room;

import java.util.List;

@Repository
public class RoomDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RoomDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long createRoom(Room room) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", room.getName())
                .addValue("capacity", room.getCapacity());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update("INSERT INTO room(name, capacity) VALUES(:name, :capacity)", params, keyHolder);
        Long generatedId = keyHolder.getKey().longValue();
        room.setId(generatedId);

        if (room.getMaterielIds() != null && !room.getMaterielIds().isEmpty()) {
            for (Integer materielId : room.getMaterielIds()) {
                MapSqlParameterSource materielParams = new MapSqlParameterSource()
                        .addValue("roomId", generatedId)
                        .addValue("materielId", materielId);
                jdbcTemplate.update("INSERT INTO room_materiel(room_id, materiel_id) VALUES(:roomId, :materielId)", materielParams);
            }
        }
        return generatedId;
    }


    public Room getRoomById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.queryForObject(
                "SELECT r.*, GROUP_CONCAT(rm.materiel_id) as materielIds FROM room r LEFT JOIN room_materiel rm ON r.id = rm.room_id WHERE r.id = :id GROUP BY r.id",
                params,
                new RoomRowMapper()
        );
    }

    public List<Room> getAllRooms() {
        return jdbcTemplate.query(
                "SELECT r.*, GROUP_CONCAT(rm.materiel_id) as materielIds FROM room r LEFT JOIN room_materiel rm ON r.id = rm.room_id GROUP BY r.id",
                new RoomRowMapper()
        );
    }

    public void updateRoom(Room room) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", room.getName())
                .addValue("capacity", room.getCapacity())
                .addValue("id", room.getId());
        jdbcTemplate.update("UPDATE room SET name= :name, capacity= :capacity WHERE id= :id", params);

        jdbcTemplate.update("DELETE FROM room_materiel WHERE room_id= :roomId", new MapSqlParameterSource().addValue("roomId", room.getId()));

        if (room.getMaterielIds() != null && !room.getMaterielIds().isEmpty()) {
            for (Integer materielId : room.getMaterielIds()) {
                MapSqlParameterSource materielParams = new MapSqlParameterSource()
                        .addValue("roomId", room.getId())
                        .addValue("materielId", materielId);
                jdbcTemplate.update("INSERT INTO room_materiel(room_id, materiel_id) VALUES(:roomId, :materielId)", materielParams);
            }
        }
    }

    public void deleteRoom(int id) {
        jdbcTemplate.update("DELETE FROM room_materiel WHERE room_id= :roomId", new MapSqlParameterSource().addValue("roomId", id));
        jdbcTemplate.update("DELETE FROM room WHERE id= :id", new MapSqlParameterSource().addValue("id", id));
    }
}
