package co.com.reactiveprogramming;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/*
To write a simple test using StepVerifier.
StepVerifier acts like a subscriber.
 */
public class Lec01MonoTest {
    private static final Logger log= LoggerFactory.getLogger(Lec01MonoTest.class);

    private Mono<String> getProduct(int id){
        return Mono.fromSupplier(()->"Product "+id)
                .doFirst(()->log.info("Invoked"));
    }


    @Test
    void productTest(){
        StepVerifier.create(getProduct(1))
                .expectNext("Product 1")
                .expectComplete()
                .verify();// subscribe
    }
}
