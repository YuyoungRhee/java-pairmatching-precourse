package pairmatching;

import pairmatching.controller.Controller;
import pairmatching.domain.PairMatcher;
import pairmatching.repository.CrewInitializer;
import pairmatching.repository.CrewRepository;

public class Application {
    public static void main(String[] args) {
        // TODO 구현 진행
        CrewRepository crewRepository = CrewRepository.getInstance();
        CrewInitializer crewInitializer = new CrewInitializer(crewRepository);
        crewInitializer.initializeCrews();

        PairMatcher pairMatcher = new PairMatcher(crewRepository.getCrews());

        Controller controller = new Controller(pairMatcher);

        controller.run();
    }
}
