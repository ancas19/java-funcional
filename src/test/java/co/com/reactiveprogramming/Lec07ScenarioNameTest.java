package co.com.reactiveprogramming;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec07ScenarioNameTest {
    private Flux<Integer> getItems(){
        return Flux.range(1,3);
    }

    @Test
    void rangeTest(){
        StepVerifierOptions options = StepVerifierOptions.create().scenarioName("1 to 3 items test");
        StepVerifier.create(getItems(),options)
                .expectNext(1)
                .as("Firtst item should be 1")
                .expectNext(2,3)
                .as("Second item should be 2 and third item should be 3")
                .expectComplete()
                .verify();
    }
}
