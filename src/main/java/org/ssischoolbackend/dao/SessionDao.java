package org.ssischoolbackend.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.ssischoolbackend.model.Session;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Repository
public class SessionDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    Properties sqlProperties;
    @Autowired
private TeacherDao teacherDao;
@Autowired
private TimeslotDao timeslotDao;
@Autowired
private SubjectDao subjectDao;
@Autowired
private RoomDao roomDao;
@Autowired
private ClassDao classDao;

public long createSession(Session session) {
    KeyHolder holder = new GeneratedKeyHolder();
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
            .addValue("teacher_id", session.getTeacher().getId())
            .addValue("class_id", session.getClassEntity().getId())
            .addValue("room_id", session.getRoom().getId())
            .addValue("timeslot_id", session.getTimeslot().getId())
            .addValue("subject_id", session.getSubject().getId()); // Added subject_id

    int insert = jdbcTemplate.update(sqlProperties.getProperty("session.create"), sqlParameterSource, holder);
    if (insert == 1) {
        log.debug("New session created: " + session.getId());
        return Objects.requireNonNull(holder.getKey()).longValue();
    } else {
        log.error("Session not created.");
        return 0;
    }
}


    public Optional<Session> getSessionById(Long id) {
    SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
    try {
        Session session = jdbcTemplate.queryForObject(
                sqlProperties.getProperty("session.get.one"),
                namedParameters,
                (ResultSet rs, int rowNum) -> Session.baseMapper(rs, rowNum, teacherDao, timeslotDao, subjectDao, roomDao, classDao)
        );
        return Optional.ofNullable(session);
    } catch (EmptyResultDataAccessException e) {
        log.error("Session with id: " + id + " does not exist.", e);
        return Optional.empty();
    } catch (DataAccessException e) {
        log.error("Error retrieving session with id: " + id, e);
        return Optional.empty();
    }
}

    

    public int updateSession(Session session) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", session.getId())
                .addValue("teacher_id", session.getTeacher().getId())
                .addValue("class_id", session.getClassEntity().getId())
                .addValue("room_id", session.getRoom().getId())
                .addValue("timeslot_id", session.getTimeslot().getId())
                .addValue("subject_id", session.getSubject().getId()); // Added subject_id
    
        return jdbcTemplate.update(sqlProperties.getProperty("session.update"), sqlParameterSource);
    }
    

    public int deleteSession(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return jdbcTemplate.update(sqlProperties.getProperty("session.delete"), namedParameters);
    }

    public List<Session> getAllSessions() {
    try {
        return jdbcTemplate.query(
                sqlProperties.getProperty("session.get.all"),
                (ResultSet rs, int rowNum) -> Session.baseMapper(rs, rowNum, teacherDao, timeslotDao, subjectDao, roomDao, classDao)
        );
    } catch (DataAccessException e) {
        log.error("Error retrieving all sessions", e);
        return new ArrayList<>();
    }
}


public Optional<Session> findSessionByClassAndTeacherAndSubject(Long classId, Long teacherId, Long subjectId) {
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
            .addValue("class_id", classId)
            .addValue("teacher_id", teacherId)
            .addValue("subject_id", subjectId);

    try {
        Session session = jdbcTemplate.queryForObject(
                sqlProperties.getProperty("session.find.by.class.teacher.subject"),
                sqlParameterSource,
                (ResultSet rs, int rowNum) -> Session.baseMapper(rs, rowNum, teacherDao, timeslotDao, subjectDao, roomDao, classDao)
        );
        return Optional.ofNullable(session);
    } catch (EmptyResultDataAccessException e) {
        return Optional.empty(); // No session found
    } catch (DataAccessException e) {
        log.error("Error retrieving session with class ID: " + classId + ", teacher ID: " + teacherId + ", subject ID: " + subjectId, e);
        return Optional.empty(); // Handle error
    }
}

}


