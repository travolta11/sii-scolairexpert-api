package org.ssischoolbackend.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.ssischoolbackend.dao.ClassDao;
import org.ssischoolbackend.dao.RoomDao;
import org.ssischoolbackend.dao.SubjectDao;
import org.ssischoolbackend.dao.TeacherDao;
import org.ssischoolbackend.dao.TimeslotDao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder

@Data
@NoArgsConstructor
@AllArgsConstructor
@PlanningEntity
public class Session {

   
    @PlanningId
    private Long id;

    @PlanningVariable(valueRangeProviderRefs = "availableTeachers")
    private Teacher teacher;

    @PlanningVariable(valueRangeProviderRefs = "availableClasses")
    private Class classEntity;

    @PlanningVariable(valueRangeProviderRefs = "availableRooms")
    private Room room;

    @PlanningVariable(valueRangeProviderRefs = "availableTimeslots")
    private Timeslot timeslot;

    @PlanningVariable(valueRangeProviderRefs = "availableSubjects")
    private Subject subject;

    // Setter for ClassEntity (already included in your original code)
    public void setClassEntity(Class classEntity) {
        this.classEntity = classEntity;
    }

    // Static method to map from ResultSet, placed in a service or factory class
    public static Session baseMapper(ResultSet resultSet, int rowNum,
    TeacherDao teacherDao,
    TimeslotDao timeslotDao,
    SubjectDao subjectDao,
    RoomDao roomDao,
    ClassDao classDao) throws SQLException {
return Session.builder()
.id(resultSet.getLong("id"))
.teacher(teacherDao.getTeacherById(resultSet.getLong("teacher_id")).orElse(null))
.classEntity(classDao.getClassById(resultSet.getLong("class_id")))
.room(roomDao.getRoomById(resultSet.getInt("room_id")))
.timeslot(timeslotDao.getTimeslotById(resultSet.getLong("timeslot_id")).orElse(null))
.subject(subjectDao.getSubjectById(resultSet.getLong("subject_id")))
.build();
}



}

  

