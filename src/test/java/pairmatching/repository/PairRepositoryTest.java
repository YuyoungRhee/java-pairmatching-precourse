package pairmatching.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Pair;

class PairRepositoryTest {

    @BeforeEach
    void setUp() {
        PairRepository.getInstance().clear();
    }

    @DisplayName("같은 레벨에서 이미 페어를 맺은 크루가 있을 시 true를 반환한다.")
    @Test
    void existSamePairInSameLevel() {
        // given
        CrewRepository crewRepository = CrewRepository.getInstance();
        CrewInitializer crewInitializer = new CrewInitializer(crewRepository);
        crewInitializer.initializeCrews();

        PairRepository pairRepository = PairRepository.getInstance();

        Course course = Course.BACKEND;
        Level level = Level.LEVEL1;

        Pair pair1 = createPair(course, level, "자동차경주", Arrays.asList("크루1", "크루2"));
        pairRepository.addPair(pair1);

        Pair pair2 = createPair(course, level, "로또", Arrays.asList("크루1", "크루2"));

        // when
        boolean result = pairRepository.existSamePairInSameLevel(pair2);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("같은 레벨에서 이미 페어를 맺은 크루가 없을 시 false를 반환한다.")
    @Test
    void existSamePairInSameLevel_False() {
        // given
        CrewRepository crewRepository = CrewRepository.getInstance();
        CrewInitializer crewInitializer = new CrewInitializer(crewRepository);
        crewInitializer.initializeCrews();

        PairRepository pairRepository = PairRepository.getInstance();

        Course course = Course.BACKEND;
        Level level = Level.LEVEL1;

        Pair pair1 = createPair(course, level, "자동차경주", Arrays.asList("크루1", "크루2"));
        pairRepository.addPair(pair1);

        Pair pair2 = createPair(course, level, "로또", Arrays.asList("크루3", "크루2"));

        // when
        boolean result = pairRepository.existSamePairInSameLevel(pair2);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("과정, 레벨, 미션으로 페어를 찾는다.")
    @Test
    void findPair() {
        // given
        CrewRepository crewRepository = CrewRepository.getInstance();
        CrewInitializer crewInitializer = new CrewInitializer(crewRepository);
        crewInitializer.initializeCrews();

        PairRepository pairRepository = PairRepository.getInstance();

        Course course = Course.BACKEND;
        Level level = Level.LEVEL1;
        String mission = "자동차경주";

        Pair pair1 = createPair(course, level, mission, Arrays.asList("크루1", "크루2"));
        pairRepository.addPair(pair1);

        Pair pair2 = createPair(course, level, mission, Arrays.asList("크루3", "크루4"));
        pairRepository.addPair(pair2);


        // when
        List<Pair> result = pairRepository.findPair(course, level, mission);

        // then
        assertThat(result).hasSize(2);
    }

    @DisplayName("페어매칭 기록이 있으면 true를 반환한다.")
    @Test
    void hasMatchHistory() {
        // given
        CrewRepository crewRepository = CrewRepository.getInstance();
        CrewInitializer crewInitializer = new CrewInitializer(crewRepository);
        crewInitializer.initializeCrews();

        PairRepository pairRepository = PairRepository.getInstance();

        Course course = Course.BACKEND;
        Level level = Level.LEVEL1;
        String mission = "자동차경주";

        Pair pair1 = createPair(course, level, mission, Arrays.asList("크루1", "크루2"));
        pairRepository.addPair(pair1);

        Pair pair2 = createPair(course, level, mission, Arrays.asList("크루3", "크루4"));
        pairRepository.addPair(pair2);


        // when
        boolean result = pairRepository.hasMatchHistory(course, level, mission);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("페어매칭 기록이 없으면 false를 반환한다.")
    @Test
    void hasMatchHistory_False() {
        // given
        CrewRepository crewRepository = CrewRepository.getInstance();
        CrewInitializer crewInitializer = new CrewInitializer(crewRepository);
        crewInitializer.initializeCrews();

        PairRepository pairRepository = PairRepository.getInstance();

        Course course = Course.BACKEND;
        Level level = Level.LEVEL1;
        String mission = "자동차경주";

        // when
        boolean result = pairRepository.hasMatchHistory(course, level, mission);

        // then
        assertThat(result).isFalse();
    }

    private Pair createPair(Course course, Level level, String mission, List<String> crewNames) {
        List<Crew> crews = new ArrayList<>();
        for (String crewName : crewNames) {
            crews.add(new Crew(course, crewName));
        }
        return new Pair(course, level, mission, new HashSet<>(crews));
    }

}