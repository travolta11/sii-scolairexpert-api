package org.ssischoolbackend.solver;

import java.time.Duration;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;
import org.ssischoolbackend.model.Session;

public class TimetableConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                // Hard constraints
                roomConflict(constraintFactory),
                teacherConflict(constraintFactory),
                classConflict(constraintFactory),
                // Soft constraints
                teacherRoomStability(constraintFactory),
                teacherTimeEfficiency(constraintFactory),
                classTimeEfficiency(constraintFactory)
        };
    }

    private Constraint roomConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Session.class)
                .join(Session.class,
                        Joiners.equal(Session::getTimeslot),
                        Joiners.equal(Session::getRoom),
                        Joiners.lessThan(Session::getId)) // Assurez-vous de comparer les IDs pour éviter les auto-conflits
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Room conflict");
    }
    

    private Constraint teacherConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Session.class)
                .join(Session.class,
                        Joiners.equal(Session::getTimeslot),
                        Joiners.equal(Session::getTeacher),
                        Joiners.lessThan(Session::getId))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Teacher conflict");
    }
    

    private Constraint classConflict(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Session.class)
                .join(Session.class,
                        Joiners.equal(Session::getTimeslot),
                        Joiners.equal(Session::getClass),
                        Joiners.lessThan(Session::getId))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Class conflict");
    }
    

    private Constraint teacherRoomStability(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach in the same room.
        return constraintFactory
                .forEachUniquePair(Session.class,
                        Joiners.equal(Session::getTeacher))
                .filter((session1, session2) -> !session1.getRoom().equals(session2.getRoom()))
                .penalize(HardSoftScore.ONE_SOFT)
                .asConstraint("Teacher room stability");
    }

    private Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach sequential sessions and dislikes gaps between lessons.
        return constraintFactory
                .forEach(Session.class)
                .join(Session.class, Joiners.equal(Session::getTeacher),
                        Joiners.equal(session -> session.getTimeslot().getDayOfWeek()))
                .filter((session1, session2) -> {
                    Duration gap = Duration.between(session1.getTimeslot().getEndTime(), session2.getTimeslot().getStartTime());
                    return !gap.isNegative() && gap.toMinutes() <= 30;
                })
                .reward(HardSoftScore.ONE_SOFT)
                .asConstraint("Teacher time efficiency");
    }

    private Constraint classTimeEfficiency(ConstraintFactory constraintFactory) {
        // A class prefers sequential sessions without gaps on the same day.
        return constraintFactory
                .forEach(Session.class)
                .join(Session.class, Joiners.equal(Session::getClass),
                        Joiners.equal(session -> session.getTimeslot().getDayOfWeek()))
                .filter((session1, session2) -> {
                    Duration gap = Duration.between(session1.getTimeslot().getEndTime(), session2.getTimeslot().getStartTime());
                    return !gap.isNegative() && gap.toMinutes() <= 30;
                })
                .reward(HardSoftScore.ONE_SOFT)
                .asConstraint("Class time efficiency");
    }
}
