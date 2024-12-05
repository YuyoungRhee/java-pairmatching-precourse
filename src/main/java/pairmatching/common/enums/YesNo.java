package pairmatching.common.enums;

import static pairmatching.common.error.ErrorMessage.NOT_IN_YES_NO;

public enum YesNo {
    YES("네"),
    NO("아니오");

    private final String value;

    YesNo(String value) {
        this.value = value;
    }

    public static YesNo from(String input) {
        for (YesNo yesNo : YesNo.values()) {
            if (yesNo.value.equalsIgnoreCase(input.trim())) {
                return yesNo;
            }
        }
        throw new IllegalArgumentException(NOT_IN_YES_NO.getMessage());
    }

    public static boolean isYes(YesNo yesNo) {
        return yesNo.equals(YES);
    }
}
