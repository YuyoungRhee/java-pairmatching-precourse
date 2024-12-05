package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pairmatching.error.ErrorMessage.NOT_FOUND_MISSION;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LevelTest {

    @DisplayName("해당 레벨에 존재하지 않는 미션인 경우 예외를 발생시킨다.")
    @Test
    void validateMissionName() {
        assertThatThrownBy(() -> {
            Level.validateMissionName(Level.LEVEL1, "오징어게임");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_FOUND_MISSION.getMessage());

    }

}