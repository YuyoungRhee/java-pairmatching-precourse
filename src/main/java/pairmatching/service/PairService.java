package pairmatching.service;

import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Pair;
import pairmatching.domain.PairMatcher;
import pairmatching.repository.PairRepository;

public class PairService {
    private final PairMatcher pairMatcher;
    private final PairRepository pairRepository;

    public PairService(PairMatcher pairMatcher, PairRepository pairRepository) {
        this.pairMatcher = pairMatcher;
        this.pairRepository = pairRepository;
    }

    public List<Pair> matchPairs(Course course, Level level, String mission) {
        pairMatcher.matchPair(course, level, mission);
        return pairRepository.findPair(course, level, mission);
    }

    public boolean hasMatchingHistory(Course course, Level level, String mission) {
        return pairRepository.hasMatchHistory(course, level, mission);
    }

    public void clearPairs() {
        pairRepository.clear();
    }
}