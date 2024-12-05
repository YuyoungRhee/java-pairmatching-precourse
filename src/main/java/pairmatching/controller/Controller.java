package pairmatching.controller;


import java.util.List;
import pairmatching.common.enums.MainOption;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Pair;
import pairmatching.domain.PairMatcher;
import pairmatching.dto.InputForMatchDto;
import pairmatching.repository.PairRepository;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Controller {
    private final InputView inputView = new InputView();
    private final InputHandler inputHandler = new InputHandler(inputView);
    private final OutputView outputView = new OutputView();
    private final PairMatcher pairMatcher;

    public Controller(PairMatcher pairMatcher) {
        this.pairMatcher = pairMatcher;
    }

    public void run() {
        PairRepository pairRepository = PairRepository.getInstance();

        while(true) {
            outputView.displayStart();
            MainOption option = inputHandler.getOption();

            if (option.equals(MainOption.PAIR_MATCHING)) {
                runPairMatch(pairRepository);
            }

            if (option.equals(MainOption.PAIR_FIND)) {
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

        boolean proceedMatch = true;
        boolean hasHistory = pairRepository.hasMatchHistory(course, level, mission);
        if (hasHistory) {
            boolean isYes = inputHandler.askYesOrNo();
            if (!isYes) {
                proceedMatch = false;
            }
        }

        if (proceedMatch) {
            pairMatcher.matchPair(course, level, mission);
        }

        List<Pair> pairs = pairRepository.findPair(course, level, mission);
        outputView.displayFindPair(pairs);
    }

    private void runPairFind(PairRepository pairRepository) {
        InputForMatchDto matchInform = inputHandler.getMatchInform();
        Course course = matchInform.getCourse();
        Level level = matchInform.getLevel();
        String mission = matchInform.getMission();

        List<Pair> pairs = pairRepository.findPair(course, level, mission);
        outputView.displayFindPair(pairs);
    }

    private void runPairInitialize(PairRepository pairRepository) {
        pairRepository.clear();
    }

}
