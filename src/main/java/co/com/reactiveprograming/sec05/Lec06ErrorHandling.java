package co.com.reactiveprograming.sec05;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
    How to handle error in a reactive pipeline
    Flux.Flux.(....)
    ...
    ...
    ...
    ...
    ...
 */
public class Lec06ErrorHandling {
    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);
    public static void main(String[] args) {

        Flux.range(1,10)
                .map(i->i==5?5/0:i)
                .onErrorContinue((e, item) -> log.info("onErrorContinue: {}",item))
                .subscribe(Util.subscriber());
    }

    //Whne you want to return a hardcode value/ simple computation
    private static void onErrorReturn(){
        Mono.just(5)
                .map(i->i==5?5/0:i)//intentional
                //.onErrorReturn(-1)
                .onErrorReturn(IllegalArgumentException.class,-1)
                .onErrorReturn(ArithmeticException.class,-2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }

    //When you have to use another pusblisher in case of error
    private static void onErrorResume(){
        Mono.error(new RuntimeException("onErrorResume"))
                .onErrorResume(ArithmeticException.class,e->fallback1())
                .onErrorResume(ex->fallback2())
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback1(){
        return Mono.fromSupplier(()->Util.faker().random().nextInt(10,100));
    }

    private static Mono<Integer> fallback2(){
        return Mono.error(new IllegalArgumentException("fallback2"));
    }
}
