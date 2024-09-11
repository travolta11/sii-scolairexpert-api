package org.ssischoolbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverStatus;
import org.optaplanner.core.api.domain.solution.PlanningScore;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@PlanningSolution
public class Timetable {


    private SolverStatus solverStatus;
    
    @ProblemFactCollectionProperty
    private List<Class> classList;

    @ProblemFactCollectionProperty
    private List<Room> roomList;

    @ProblemFactCollectionProperty
    private List<Subject> subjectList;

    @ProblemFactCollectionProperty
    private List<Teacher> teacherList;

    @ProblemFactCollectionProperty
    private List<Timeslot> timeslotList;

    @PlanningEntityCollectionProperty
    private List<Session> sessionList;


    @PlanningScore
    private HardSoftScore score;

    @ValueRangeProvider(id = "availableRooms")
    public List<Room> getRoomList() {
        return roomList;
    }

    @ValueRangeProvider(id = "availableTeachers")
    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    @ValueRangeProvider(id = "availableClasses")
    public List<Class> getClassList() {
        return classList;
    }

    @ValueRangeProvider(id = "availableTimeslots")
    public List<Timeslot> getAvailableTimeslots() {
        return timeslotList;
    }

    @ValueRangeProvider(id = "availableSubjects")
    public List<Subject> getSubjectList() {
        return subjectList;
    }

      public Timetable(List<Timeslot> timeslotList, List<Room> roomList, List<Teacher> teacherList,
                     List<Subject> subjectList, List<Class> classList, List<Session> sessionList) {
        this.timeslotList = timeslotList;
        this.roomList = roomList;
        this.teacherList = teacherList;
        this.subjectList = subjectList;
        this.classList = classList;
        this.sessionList = sessionList != null ? sessionList : new ArrayList<>();
    }
  
   
}
