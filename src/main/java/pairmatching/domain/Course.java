package pairmatching.domain;

import static pairmatching.common.error.ErrorMessage.NOT_FOUND_COURSE;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course from(String input) {
        for (Course course: Course.values()) {
            if(course.name.equals(input)) {
                return course;
            }
        }
        throw new IllegalArgumentException(NOT_FOUND_COURSE.getMessage());
    }
}