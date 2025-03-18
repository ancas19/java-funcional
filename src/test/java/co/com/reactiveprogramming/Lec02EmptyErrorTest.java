package co.com.reactiveprogramming;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec02EmptyErrorTest {

    private Mono<String> getUsername(int userId){
        return switch (userId){
            case 1 -> Mono.just("Sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("User not found"));
        };
    }

    @Test
    void userTest(){
        StepVerifier.create(getUsername(1))
                .expectNext("Sam")
                .expectComplete()
                .verify();// subscribe
    }

    @Test
    void emptyTest(){
        StepVerifier.create(getUsername(2))
                .expectComplete()
                .verify();// subscribe
    }


    @Test
    void errorTest1(){
        StepVerifier.create(getUsername(3))
                .expectError()
                .verify();
    }

    @Test
    void errorTest2(){
        StepVerifier.create(getUsername(3))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void errorTest3(){
        StepVerifier.create(getUsername(3))
                .expectErrorMessage("User not found")
                .verify();
    }

    @Test
    void errorTest4(){
        StepVerifier.create(getUsername(3))
                .consumeErrorWith(ex->{
                    assert ex instanceof RuntimeException;
                    assert ex.getMessage().equals("User not found");
                })
                .verify();
    }
}
