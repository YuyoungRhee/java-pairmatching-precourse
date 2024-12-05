package pairmatching;

import pairmatching.controller.Controller;
import pairmatching.domain.PairMatcher;
import pairmatching.repository.CrewInitializer;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.PairRepository;
import pairmatching.service.PairService;

public class Application {
    public static void main(String[] args) {
        // TODO 구현 진행
        CrewRepository crewRepository = CrewRepository.getInstance();
        CrewInitializer crewInitializer = new CrewInitializer(crewRepository);
        crewInitializer.initializeCrews();

        PairMatcher pairMatcher = new PairMatcher(crewRepository.getCrews());
        PairService pairService = new PairService(pairMatcher, PairRepository.getInstance());

        Controller controller = new Controller(pairService);

        controller.run();
    }
}
