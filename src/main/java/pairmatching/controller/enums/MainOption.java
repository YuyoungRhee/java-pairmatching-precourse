package pairmatching.controller.enums;

import static pairmatching.error.ErrorMessage.NO_MAIN_OPTION;

import java.util.Arrays;

public enum MainOption {
    PAIR_MATCHING("1"),
    PAIR_FIND("2"),
    PAIR_INITIALIZE("3"),
    QUIT("Q");

    private final String command;

    MainOption(String command) {
        this.command = command;
    }

    public static MainOption from(String command) {
        return Arrays.stream(MainOption.values())
                .filter(option -> option.command.equals(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_MAIN_OPTION.getMessage()));
    }

}