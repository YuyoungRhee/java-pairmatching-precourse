package pairmatching.controller;


import static pairmatching.error.ErrorMessage.NO_MATCHING_HISTORY;

import java.util.List;
import pairmatching.controller.enums.MainOption;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Pair;
import pairmatching.domain.PairMatcher;
import pairmatching.dto.InputForMatchDto;
import pairmatching.repository.PairRepository;
import pairmatching.service.PairService;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Controller {
    private final InputView inputView = new InputView();
    private final InputHandler inputHandler = new InputHandler(inputView);
    private final OutputView outputView = new OutputView();
    private final PairMatcher pairMatcher;
    private final PairService pairService;

    public Controller(PairMatcher pairMatcher, PairService pairService) {
        this.pairMatcher = pairMatcher;
        this.pairService = pairService;
    }

    public void run() {
        PairRepository pairRepository = PairRepository.getInstance();

        while(true) {
            MainOption option = inputHandler.getOption();

            if (option.equals(MainOption.PAIR_MATCHING)) {
                outputView.displayInformPage();
                runPairMatch(pairRepository);
            }

            if (option.equals(MainOption.PAIR_FIND)) {
                outputView.displayInformPage();
                runPairFind(pairRepository);
            }

            if (option.equals(MainOption.PAIR_INITIALIZE)) {
                runPairInitialize(pairRepository);
            }

            if (option.equals(MainOption.QUIT)) {
                return;
            }
        }

    }

    private void runPairMatch(PairRepository pairRepository) {
        InputForMatchDto matchInform = inputHandler.getMatchInform();
        Course course = matchInform.getCourse();
        Level level = matchInform.getLevel();
        String mission = matchInform.getMission();

        if (pairService.hasMatchingHistory(course, level, mission)) {
            if (!inputHandler.askYesOrNo()) {
                return;
            }
        }

        List<Pair> pairs = pairService.matchPairs(course, level, mission);
        outputView.displayFindPair(pairs);
    }

    private void runPairFind(PairRepository pairRepository) {
        InputForMatchDto matchInform = inputHandler.getMatchInform();
        Course course = matchInform.getCourse();
        Level level = matchInform.getLevel();
        String mission = matchInform.getMission();

        if (!pairService.hasMatchingHistory(course, level, mission)) {
            outputView.displayError(NO_MATCHING_HISTORY.getMessage());
            return;
        }

        List<Pair> pairs = pairService.matchPairs(course, level, mission);
        outputView.displayFindPair(pairs);
    }

    private void runPairInitialize(PairRepository pairRepository) {
        pairService.clearPairs();
        outputView.displayInitializationComplete();
    }

}
