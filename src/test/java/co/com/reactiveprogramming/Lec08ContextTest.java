package co.com.reactiveprogramming;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class Lec08ContextTest {
    private Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx->{
            if(ctx.hasKey("user")){
                return Mono.just("Welcome "+ctx.get("user"));
            }
            return Mono.error(new RuntimeException("Unauthorized"));
        });
    }

    @Test
    void contextTest(){
        StepVerifierOptions options = StepVerifierOptions.create().withInitialContext(Context.of("user","Andrés"));
        StepVerifier.create(getWelcomeMessage(),options)
                .expectNext("Welcome Andrés")
                .expectComplete()
                .verify();
    }

    @Test
    void contextTest1(){
        StepVerifierOptions options = StepVerifierOptions.create().withInitialContext(Context.empty());
        StepVerifier.create(getWelcomeMessage(),options)
                .expectErrorMessage("Unauthorized")
                .verify();
    }
}
