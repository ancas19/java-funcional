package co.com.reactiveprograming.sec11;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*
Retry operator simply resubscribes when it sees error signal
 */
public class Lec02Retry {
    private static final Logger log= LoggerFactory.getLogger(Lec02Retry.class);

    public static void main(String[] args) {
        demo2();

        Util.sleepSeconds(10);
    }

    private static void demo1(){
        getCountryName()
                .retry(2)
                .subscribe(Util.subscriber());
    }

    private static void demo2(){
        getCountryName()
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(1))
                                .filter(ex->ex instanceof RuntimeException)
                                .onRetryExhaustedThrow((spec,signal)->signal.failure())
                                .doBeforeRetry(s->log.info("Retrying..."))
                )
                .subscribe(Util.subscriber());
    }
    private static Mono<String> getCountryName(){
        AtomicInteger count = new AtomicInteger(0);
        return Mono.fromSupplier(()->{
            if(count.incrementAndGet()<3){
                throw new RuntimeException("Error");
            }
            return Util.faker().country().name();
        }).doOnError(e->log.info("Error: {}",e.getMessage()))
                .doOnSubscribe(s->log.info("Subscribed"));
    }
}
