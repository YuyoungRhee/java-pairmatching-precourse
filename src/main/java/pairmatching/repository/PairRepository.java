package pairmatching.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Pair;

public class PairRepository {
    private static final PairRepository Singleton = new PairRepository();

    private final List<Pair> pairs = new ArrayList<>();

    public static PairRepository getInstance() {
        return Singleton;
    }

    public void addPair(Pair pair) {
        pairs.add(pair);
    }

    public boolean existSamePairInSameLevel(Pair comparePair) {
        long count = pairs.stream()
                .filter(pair -> pair.isSamePairInSameLevel(comparePair))
                .count();
        return count > 0;
    }

    public List<Pair> findPair(Course course, Level level, String mission) {
        return pairs.stream()
                .filter(pair -> pair.meetCondition(course, level, mission))
                .collect(Collectors.toList());
    }

    public boolean hasMatchHistory(Course course, Level level, String mission) {
        long count = pairs.stream()
                .filter(pair -> pair.meetCondition(course, level, mission))
                .count();

        return count > 0;
    }
}
