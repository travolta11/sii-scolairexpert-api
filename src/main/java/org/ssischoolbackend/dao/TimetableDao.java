package org.ssischoolbackend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.ssischoolbackend.model.Class;
import org.ssischoolbackend.model.Room;
import org.ssischoolbackend.model.Session;
import org.ssischoolbackend.model.Subject;
import org.ssischoolbackend.model.Teacher;
import org.ssischoolbackend.model.Timeslot;
import org.ssischoolbackend.model.Timetable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
@Transactional
public class TimetableDao {

    public static final Long SINGLETON_TIME_TABLE_ID = 1L;

    @Autowired
    private TimeslotDao timeslotDao;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private ClassDao classDao;
    @Autowired
    private SessionDao sessionDao;

    public Timetable findById(Long id) {
        if (!SINGLETON_TIME_TABLE_ID.equals(id)) {
            throw new IllegalStateException("There is no timetable with id (" + id + ").");
        }
    
        List<Timeslot> timeslotList = timeslotDao.getAllTimeslots();
        List<Room> roomList = roomDao.getAllRooms();
        List<Teacher> teacherList = teacherDao.getAllTeachers();
        List<Subject> subjectList = subjectDao.getAllSubjects();
        List<Class> classList = classDao.getAllClasses();
    
        // Charger les sessions existantes depuis la base de données
        List<Session> sessionList = sessionDao.getAllSessions();
    
        return new Timetable(
                timeslotList,
                roomList,
                teacherList,
                subjectList,
                classList,
                sessionList
        );
    }

    

    public void save(Timetable timetable) {
        List<Session> sessionsToSave = timetable.getSessionList();

        for (Session session : sessionsToSave) {
            Optional<Session> existingSessionOpt = sessionDao.getSessionById(session.getId());

            if (existingSessionOpt.isPresent()) {
                // Update existing session
                Session existingSession = existingSessionOpt.get();
                existingSession.setTimeslot(session.getTimeslot());
                existingSession.setRoom(session.getRoom());
                existingSession.setTeacher(session.getTeacher());
                existingSession.setSubject(session.getSubject());
                existingSession.setClassEntity(session.getClassEntity());

                sessionDao.updateSession(existingSession);
            } else {
                // Create new session
                sessionDao.createSession(session);
            }
        }
    }
}