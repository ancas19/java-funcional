package co.com.reactiveprogramming;

import co.com.reactiveprograming.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec04RangeTest {

    private Flux<Integer> getItems(){
        return Flux.range(1,50);
    }

    private Flux<Integer> getRamdomItems(){
        return Flux.range(1,50)
                .map(i-> Util.faker().random().nextInt(1,100));
    }

    @Test
    void rangeTest(){
        StepVerifier.create(getItems())
                .expectNext(1)
                .expectNextCount(49)
                .expectComplete()
                .verify();
    }

    @Test
    void rangeTest2(){
        StepVerifier.create(getItems())
                .expectNext(1)
                .expectNextCount(22)
                .expectNext(24)
                .expectNextCount(26)
                .expectComplete()
                .verify();
    }

    @Test
    void rangeTest3(){
        StepVerifier.create(getRamdomItems())
                .expectNextMatches(i->i>=1 && i<=100)
                .expectNextCount(49)
                .expectComplete()
                .verify();
    }

    @Test
    void rangeTest4(){
        StepVerifier.create(getRamdomItems())
                .thenConsumeWhile(i->i>=1 && i<=100)
                .expectComplete()
                .verify();
    }
}
