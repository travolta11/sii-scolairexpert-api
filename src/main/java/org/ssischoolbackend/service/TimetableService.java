// package org.ssischoolbackend.service;

// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;
// import java.util.concurrent.atomic.AtomicLong;

// import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
// import org.optaplanner.core.api.solver.SolutionManager;
// import org.optaplanner.core.api.solver.SolverManager;
// import org.optaplanner.core.api.solver.SolverStatus;

// import org.ssischoolbackend.dao.ClassDao;
// import org.ssischoolbackend.dao.RoomDao;
// import org.ssischoolbackend.dao.SessionDao;
// import org.ssischoolbackend.dao.SubjectDao;
// import org.ssischoolbackend.dao.TeacherDao;
// import org.ssischoolbackend.dao.TimeslotDao;
// import org.ssischoolbackend.model.Class;
// import org.ssischoolbackend.model.Room;
// import org.ssischoolbackend.model.Session;
// import org.ssischoolbackend.model.Subject;
// import org.ssischoolbackend.model.Teacher;
// import org.ssischoolbackend.model.Timeslot;
// import org.ssischoolbackend.model.Timetable;

// @Service
// public class TimetableService {

//     private static final Long SINGLETON_TIME_TABLE_ID = 1L;

//     @Autowired
//     private TimeslotDao timeslotDao;
//     @Autowired
//     private RoomDao roomDao;
//     @Autowired
//     private TeacherDao teacherDao;
//     @Autowired
//     private SubjectDao subjectDao;
//     @Autowired
//     private ClassDao classDao;
//     @Autowired
//     private SessionDao sessionDao;

//     @Autowired
//     private SolverManager<Timetable, Long> solverManager;
//     @Autowired
//     private SolutionManager<Timetable, HardSoftScore> solutionManager;

//     public Timetable getTimeTable() {
//         SolverStatus solverStatus = getSolverStatus();
//         Timetable solution = findById(SINGLETON_TIME_TABLE_ID);
//         solutionManager.update(solution);
//         solution.setSolverStatus(solverStatus);
//         return solution;
//     }

//     public void solve() {
//         solverManager.solveAndListen(SINGLETON_TIME_TABLE_ID,
//                 this::findById,
//                 this::save);
//         System.out.println("Solving started");
//     }

//     public SolverStatus getSolverStatus() {
//         return solverManager.getSolverStatus(SINGLETON_TIME_TABLE_ID);
//     }

//     public void stopSolving() {
//         solverManager.terminateEarly(SINGLETON_TIME_TABLE_ID);
//     }

//   @Transactional
// protected Timetable findById(Long id) {
//     if (!SINGLETON_TIME_TABLE_ID.equals(id)) {
//         throw new IllegalStateException("There is no timetable with id (" + id + ").");
//     }

//     List<Session> sessionList = sessionDao.getAllSessions();
    
//     if (sessionList.isEmpty()) {
//         List<Class> classes = classDao.getAllClasses();
//         List<Subject> subjects = subjectDao.getAllSubjects();

//         // Generate sessions based on classes and subjects
//         for (Class clazz : classes) {
//             for (Subject subject : subjects) {
//                 if (subject.getTeacherId() != null) { // Assuming a subject must have a teacher
//                     Session session = new Session();
//                     session.setId(generateUniqueId());
//                     session.setClassEntity(clazz);
//                     session.setSubject(subject);
//                     session.setTeacher(teacherDao.getTeacherById(subject.getTeacherId()).orElse(null)); // Fetch teacher if exists
//                     session.setRoom(roomDao.getAllRooms().stream().findFirst().orElse(null)); // Assign a default room if needed
//                     session.setTimeslot(timeslotDao.getAllTimeslots().stream().findFirst().orElse(null)); // Assign a default timeslot if needed

//                     sessionList.add(session);
//                 }
//             }
//         }
//     }

//     return new Timetable(
//             timeslotDao.getAllTimeslots(),
//             roomDao.getAllRooms(),
//             teacherDao.getAllTeachers(),
//             subjectDao.getAllSubjects(),
//             classDao.getAllClasses(),
//             sessionList
//     );
// }

//     private static final AtomicLong counter = new AtomicLong();

//     private Long generateUniqueId() {
//         return counter.incrementAndGet();
//     }

//     @Transactional
//     protected void save(Timetable timetable) {
//         for (Session session : timetable.getSessionList()) {
//             Optional<Session> optionalSession = sessionDao.getSessionById(session.getId());
//             if (optionalSession.isPresent()) {
//                 Session attachedSession = optionalSession.get();
//                 attachedSession.setTimeslot(session.getTimeslot());
//                 attachedSession.setRoom(session.getRoom());
//                 attachedSession.setTeacher(session.getTeacher());
//                 attachedSession.setSubject(session.getSubject());
//                 attachedSession.setClassEntity(session.getClassEntity());

//                 sessionDao.updateSession(attachedSession);
//             } else {
//                 // Save new session if it doesn't exist
//                 sessionDao.createSession(session);
//             }
//         }
//     }
// }
