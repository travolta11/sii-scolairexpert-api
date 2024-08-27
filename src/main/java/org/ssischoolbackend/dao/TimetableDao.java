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

        List<Session> sessionList = sessionDao.getAllSessions();

        // If sessions are empty, generate new sessions based on valid Class and Teacher combinations
        if (sessionList.isEmpty()) {
            sessionList = generateSessions(classList, subjectList, teacherList, roomList, timeslotList);
        }

        return new Timetable(
                timeslotList,
                roomList,
                teacherList,
                subjectList,
                classList,
                sessionList
        );
    }

    private List<Session> generateSessions(List<Class> classes, List<Subject> subjects, List<Teacher> teachers,
                                            List<Room> rooms, List<Timeslot> timeslots) {

        return classes.stream()
                .flatMap(clazz -> subjects.stream()
                        .flatMap(subject -> teachers.stream()
                                .flatMap(teacher -> timeslots.stream()
                                        .flatMap(timeslot -> rooms.stream()
                                                .filter(room -> !sessionExists(clazz, teacher, subject))
                                                .map(room -> createSession(clazz, subject, teacher, room, timeslot))
                                        )
                                )
                        )
                )
                .collect(Collectors.toList());
    }

    private boolean sessionExists(Class clazz, Teacher teacher, Subject subject) {
        return sessionDao.findSessionByClassAndTeacherAndSubject(clazz.getId(), teacher.getId(), subject.getId()).isPresent();
    }

    private Session createSession(Class clazz, Subject subject, Teacher teacher, Room room, Timeslot timeslot) {
        Session session = new Session();
        session.setClassEntity(clazz);
        session.setSubject(subject);
        session.setTeacher(teacher);
        session.setRoom(room);
        session.setTimeslot(timeslot);

        // Generate a unique ID or set it appropriately
        session.setId(generateUniqueId());
        return session;
    }

    private static final AtomicLong counter = new AtomicLong();

    private Long generateUniqueId() {
        return counter.incrementAndGet();
    }

    public void save(Timetable timetable) {
        for (Session session : timetable.getSessionList()) {
            Optional<Session> existingSession = sessionDao.getSessionById(session.getId());
            if (existingSession.isPresent()) {
                Session attachedSession = existingSession.get();
                attachedSession.setTimeslot(session.getTimeslot());
                attachedSession.setRoom(session.getRoom());
                attachedSession.setTeacher(session.getTeacher());
                attachedSession.setSubject(session.getSubject());
                attachedSession.setClassEntity(session.getClassEntity());

                sessionDao.updateSession(attachedSession);
            } else {
                sessionDao.createSession(session);
            }
        }
    }
}
