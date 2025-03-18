package co.com.reactiveprogramming;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec03FluxTest {
    private Flux<Integer> getItems(){
        return Flux.just(1,2,3,4,5,6)
                .log();
    }

    @Test
    void fluxTest(){
        StepVerifier.create(getItems(),1)
                .expectNext(1)
                .thenCancel()
                .verify();
    }

    @Test
    void fluxTest2(){
        StepVerifier.create(getItems())
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .expectNext(6)
                .expectComplete()
                .verify();
    }

    @Test
    void fluxTest3(){
        StepVerifier.create(getItems())
                .expectNext(1,2,3,4,5,6)
                .expectComplete()
                .verify();
    }
}
