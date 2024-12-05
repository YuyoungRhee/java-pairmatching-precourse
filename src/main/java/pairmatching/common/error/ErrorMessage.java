package pairmatching.common.error;

public enum ErrorMessage {
    NULL_OR_BLANK_INPUT("입력 값은 공백이나 빈 값일 수 없습니다."),
    NOT_FOUND_COURSE("과정명이 존재하지 않습니다.\n"),
    NOT_FOUND_LEVEL("레벨이 존재하지 않습니다.\n"),
    NOT_FOUND_MISSION("미션명이 존재하지 않습니다.\n"),
    EXCEED_ATTEMPT_COUNT("페어 매칭 시도횟수를 초과했습니다."),
    NO_MAIN_OPTION("해당하는 메인 옵션이 존재하지 않습니다."),
    NOT_IN_YES_NO("입력 값은 Y 또는 N이어야 합니다."),
    NO_MATCHING_HISTORY("매칭 이력이 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR] " + message;
    }
}