package pairmatching.controller;

import static pairmatching.common.error.ErrorMessage.NULL_OR_BLANK_INPUT;

import java.util.List;
import pairmatching.common.enums.MainOption;
import pairmatching.common.enums.YesNo;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.dto.InputForMatchDto;
import pairmatching.util.Util;
import pairmatching.view.InputView;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public MainOption getOption() {
        return RetryUtil.inputWithRetry(() -> {
            String input = inputView.readOption();
            validateNullOrBlank(input);

            MainOption userSelect = MainOption.from(input);

            return userSelect;
        });
    }

    public boolean askYesOrNo() {
        return RetryUtil.inputWithRetry(() -> {
            String input = inputView.readYesOrNo();
            validateNullOrBlank(input);

            YesNo userAnswer = YesNo.from(input);

            return YesNo.isYes(userAnswer);
        });
    }

    public InputForMatchDto getMatchInform() {
        return RetryUtil.inputWithRetry(() -> {
            String input = inputView.readMatchInform();
            validateNullOrBlank(input);
            List<String> parsed = Util.parseInputByComma(input);

            Course course = Course.from(parsed.get(0));
            Level level = Level.from(parsed.get(1));
            String mission = parsed.get(2);
            Level.validateMissionName(level, mission);

            return new InputForMatchDto(course, level, mission);
        });

    }

    private static void validateNullOrBlank(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(NULL_OR_BLANK_INPUT.getMessage());
        }
    }

}
