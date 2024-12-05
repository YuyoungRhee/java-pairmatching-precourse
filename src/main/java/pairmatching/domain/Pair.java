package pairmatching.domain;

import java.util.Set;

public class Pair {
    private final Course course;
    private final Level level;
    private final String mission;
    private final Set<Crew> crews;

    public Pair(Course course, Level level, String mission, Set<Crew> crews) {
        this.course = course;
        this.level = level;
        this.mission = mission;
        this.crews = crews;
    }

    public boolean isSamePairInSameLevel(Pair comparePair) {
        return  level.equals(comparePair.level)
                && crews.equals(comparePair.crews);
    }

    public boolean meetCondition(Course course, Level level, String mission) {
        return  this.course.equals(course) && this.level.equals(level) && this.mission.equals(mission);
    }
}
