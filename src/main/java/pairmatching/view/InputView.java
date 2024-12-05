package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readMatchInform() {
        System.out.println("과정, 레벨, 미션을 선택하세요.");
        return Console.readLine();
    }

    public String readYesOrNo() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        return Console.readLine();
    }

    public String readOption() {
        System.out.println("기능을 선택하세요.\n"
                + "1. 페어 매칭\n"
                + "2. 페어 조회\n"
                + "3. 페어 초기화\n"
                + "Q. 종료");
        return Console.readLine();
    }
}
