package pairmatching.domain;

import static pairmatching.common.error.ErrorMessage.EXCEED_ATTEMPT_COUNT;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pairmatching.repository.PairRepository;

public class PairMatcher {
    private final static int RETRY_ATTEMPT = 3;
    private final List<Crew> crews;

    public PairMatcher(List<Crew> crews) {
        this.crews = crews;
    }

    public void matchPair(Course course, Level level, String mission) {
        List<String> crewNames = getCrewNames(course);
        for (int i = 0; i < RETRY_ATTEMPT; i++) {
            List<String> shuffledCrew = Randoms.shuffle(crewNames);

            List<Pair> pairs = createPairs(course, level, mission, shuffledCrew);

            if (!isDuplicate(pairs)) {
                PairRepository pairRepository = PairRepository.getInstance();
                pairRepository.clear();
                pairRepository.addPairs(pairs);
                return;
            }
        }
        throw new IllegalArgumentException(EXCEED_ATTEMPT_COUNT.getMessage());
    }

    private List<Pair> createPairs(Course course, Level level, String mission, List<String> shuffledCrew) {
        List<Pair> pairs = new ArrayList<>();

        int i = 0;
        while (i < shuffledCrew.size()) {

            Set<Crew> crewSet = new LinkedHashSet<>();

            crewSet.add(new Crew(course, shuffledCrew.get(i)));
            crewSet.add(new Crew(course, shuffledCrew.get(i + 1)));

            if (i == shuffledCrew.size() - 3) {
                crewSet.add(new Crew(course, shuffledCrew.get(i + 2)));
                i += 1;
            }
            pairs.add(new Pair(course, level, mission, crewSet));
            i += 2;
        }
        return pairs;
    }


    private boolean isDuplicate(List<Pair> pairs) {
        PairRepository pairRepository = PairRepository.getInstance();

        return pairs.stream()
                .anyMatch(pairRepository::existSamePairInSameLevel);
    }

    private List<String> getCrewNames(Course course) {
        return crews.stream()
                .filter(crew -> crew.getCourse().equals(course))
                .map(Crew::getName)
                .collect(Collectors.toList());
    }

}
