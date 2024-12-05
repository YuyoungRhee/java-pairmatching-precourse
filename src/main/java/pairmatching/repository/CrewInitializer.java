package pairmatching.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import pairmatching.domain.Course;

public class CrewInitializer {
    private final CrewRepository crewRepository;

    public CrewInitializer(CrewRepository holidayRepository) {
        this.crewRepository = holidayRepository;
    }

    public void initializeCrews() {
        initializeCrewsFromCourse(Course.BACKEND);
        initializeCrewsFromCourse(Course.FRONTEND);
    }

    private void initializeCrewsFromCourse(Course course) {
        String fileName = getFileNameFrom(course);

        try (BufferedReader reader = createReader(fileName)) {
            String line;
            while ((line = reader.readLine()) != null) {
                processHolidayLine(line, course);
            }
        } catch (IOException e) {
            System.err.println("[ERROR] 크루 정보를 읽는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private String getFileNameFrom(Course course) {
        if (course.equals(Course.BACKEND)) {
            return "backend-crew.md";
        }
        return "frontend-crew.md";
    }

    private BufferedReader createReader(String fileName) {
        return new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName))));
    }

    private void processHolidayLine(String line, Course course) {
        validateNullOrBlank(line);
        String name = line;

        try {
            crewRepository.addCrew(course, name);
        } catch (IllegalArgumentException e) {
            System.err.println("[ERROR] 공휴일 데이터를 처리할 수 없습니다: " + line);
        }
    }

    private static void validateNullOrBlank(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("크루 데이터가 비어있습니다.");
        }
    }
}
