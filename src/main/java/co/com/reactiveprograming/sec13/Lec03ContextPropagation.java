package co.com.reactiveprograming.sec13;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class Lec03ContextPropagation {
    private static final Logger log= LoggerFactory.getLogger(Lec03ContextPropagation.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .concatWith(Flux.merge(producer1(),producer2().contextWrite(xtx->Context.empty())))
                .contextWrite(Context.of("user","Andrés"))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }


    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx->{
            if(ctx.hasKey("user")){
                return Mono.just("Welcome "+ctx.get("user"));
            }
            return Mono.error(new RuntimeException("Unauthorized"));
        });
    }

    private static Mono<String> producer1(){
        return Mono.<String>deferContextual(ctx->{
            log.info("producer 1 {}",ctx);
            return Mono.empty();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private static Mono<String> producer2(){
        return Mono.<String>deferContextual(ctx->{
            log.info("producer 2 {}",ctx);
            return Mono.empty();
        }).subscribeOn(Schedulers.parallel());
    }
}
