package pairmatching.controller;

import java.util.function.Supplier;

public class RetryUtil {

    public static <T> T inputWithRetry(Supplier<T> task) {
        while (true) {
            try {
                return task.get(); // 작업 실행
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // 예외 메시지 출력
            }
        }
    }
}
