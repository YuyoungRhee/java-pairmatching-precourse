package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.repository.CrewInitializer;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.PairRepository;

class PairMatcherTest {

    @BeforeEach
    void setUp() {
        CrewRepository.getInstance().clear();
        PairRepository.getInstance().clear();
    }
    
    @DisplayName("랜덤으로 섞인 크루리스트에서 두명씩 페어를 매칭한다.")
    @Test
    void matchPair() {
        // given
        CrewRepository crewRepository = CrewRepository.getInstance();
        CrewInitializer crewInitializer = new CrewInitializer(crewRepository);
        crewInitializer.initializeCrews();

        PairMatcher pairMatcher = new PairMatcher(crewRepository.getCrews());
        Course course = Course.BACKEND;
        Level level = Level.LEVEL1;
        String mission = "자동차경주";

        // when
        pairMatcher.matchPair(course, level, mission);

        PairRepository pairRepository = PairRepository.getInstance();

        // then
        List<Pair> pair = pairRepository.findPair(course, level, mission);
        assertThat(pair).hasSize(10); //백엔드 20명이므로 10개의 페어가 생김
    }

    @DisplayName("홀수인 경우 마지막 남은 크루는 마지막 페어에 포함시킨다.")
    @Test
    void matchPair_Odd() {
        // given
        CrewRepository crewRepository = CrewRepository.getInstance();
        CrewInitializer crewInitializer = new CrewInitializer(crewRepository);
        crewInitializer.initializeCrews();

        PairMatcher pairMatcher = new PairMatcher(crewRepository.getCrews());
        Course course = Course.FRONTEND; //15명
        Level level = Level.LEVEL1;
        String mission = "자동차경주";

        // when
        pairMatcher.matchPair(course, level, mission);

        PairRepository pairRepository = PairRepository.getInstance();

        // then
        List<Pair> pair = pairRepository.findPair(course, level, mission);
        assertThat(pair).hasSize(7); //2명*6쌍 + 3명*1쌍 -> 7쌍
    }

//    @DisplayName("같은 레벨에서 이미 페어로 만난적이 있는 크루끼리 다시 페어로 매칭 된다면 크루 목록의 순서를 다시 랜덤으로 섞어서 매칭을 시도한다.")
//    @Test
//    void match_Retry() {
//        // given
//
//
//        // when
//
//        // then
//        Assertions.assertThat()
//    }

}