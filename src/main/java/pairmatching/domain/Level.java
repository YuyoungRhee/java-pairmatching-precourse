package pairmatching.domain;

import static pairmatching.common.error.ErrorMessage.NOT_FOUND_LEVEL;
import static pairmatching.common.error.ErrorMessage.NOT_FOUND_MISSION;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Level {
    LEVEL1("레벨1", Arrays.asList("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", Arrays.asList("장바구니", "결제", "지하철노선도")),
    LEVEL3("레벨3", Collections.emptyList()),
    LEVEL4("레벨4", Arrays.asList("성능개선", "배포")),
    LEVEL5("레벨5", Collections.emptyList());

    private final String name;
    private final List<String> mission;

    Level(String name, List<String> mission) {
        this.name = name;
        this.mission = mission;
    }

    public static Level from(String input) {
        for (Level level : Level.values()) {
            if (level.name.equals(input)) {
                return level;
            }
        }
        throw new IllegalArgumentException(NOT_FOUND_LEVEL.getMessage());
    }

    public static void validateMissionName(Level level, String mission) {
        List<String> missions = level.getMission();
        boolean existInLevel = missions.stream()
                .anyMatch(s -> s.equals(mission));

        if (!existInLevel) {
            throw new IllegalArgumentException(NOT_FOUND_MISSION.getMessage());
        }
    }

    public List<String> getMission() {
        return mission;
    }
}
