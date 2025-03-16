package co.com.reactiveprograming.sec02;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {
        getUsername(3)
                .subscribe(
                        s -> System.out.println("Received: " + s),
                        err->{}
                );
    }

    private static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("John");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("User not found"));
        };
    }
}
