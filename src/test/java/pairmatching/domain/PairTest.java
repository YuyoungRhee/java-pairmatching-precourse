package pairmatching.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.repository.CrewInitializer;
import pairmatching.repository.CrewRepository;

class PairTest {

    @DisplayName("주어진 페어가, 현재 페어와 같은 레벨에서 같은 크루 구성원인지 확인한다.")
    @Test
    void isSamePairInSameLevel() {
        // given
        CrewRepository crewRepository = CrewRepository.getInstance();
        CrewInitializer crewInitializer = new CrewInitializer(crewRepository);
        crewInitializer.initializeCrews();

        Course course = Course.BACKEND;
        Level level = Level.LEVEL1;

        List<Crew> compareCrews = Arrays.asList(new Crew(course, "크루1"), new Crew(course, "크루2"));
        Pair pair1 = new Pair(course, level, "자동차경주", new HashSet<>(compareCrews));

        List<Crew> crews = Arrays.asList(new Crew(course, "크루1"), new Crew(course, "크루2"));
        Pair pair2 = new Pair(course, level, "로또", new HashSet<>(crews));

        // when
        boolean samePairInSameLevel = pair2.isSamePairInSameLevel(pair1);

        //then
        Assertions.assertThat(samePairInSameLevel).isTrue();
    }

}