package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.repository.CrewInitializer;
import pairmatching.repository.CrewRepository;

class CrewInitializerTest {

    @BeforeEach
    void setUp() {
        CrewRepository.getInstance().clear(); // Crew도 초기화 필요시 추가
    }

    @DisplayName("크루 이름을 초기화하여 레파지토리에 저장한다.")
    @Test
    void crewInitializeTest() {
        // given
        CrewRepository crewRepository = CrewRepository.getInstance();
        CrewInitializer crewInitializer = new CrewInitializer(crewRepository);

        // when
        crewInitializer.initializeCrews();

        // then
        List<Crew> crews = crewRepository.getCrews();
        assertThat(crews).isNotNull();
        assertThat(crews).hasSize(35);
    }

}