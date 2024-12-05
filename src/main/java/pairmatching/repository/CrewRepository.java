package pairmatching.repository;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;

public class CrewRepository {
    private static final CrewRepository Singleton = new CrewRepository();

    private final List<Crew> crews = new ArrayList<>();
    //private final List<Crew> backendCrews = new ArrayList<>();
    //private final List<Crew> frontendCrews = new ArrayList<>();

    public static CrewRepository getInstance() {
        return Singleton;
    }

    public void addCrew(Course course, String name) {
        crews.add(new Crew(course, name));
    }

    public List<Crew> getCrews() {
        return new ArrayList<>(crews);
    }
}
