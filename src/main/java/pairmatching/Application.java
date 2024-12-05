package pairmatching;

import pairmatching.domain.CrewInitializer;
import pairmatching.repository.CrewRepository;

public class Application {
    public static void main(String[] args) {
        // TODO 구현 진행
        CrewRepository crewRepository = CrewRepository.getInstance();
        CrewInitializer crewInitializer = new CrewInitializer(crewRepository);
        crewInitializer.initializeCrews();
    }
}
