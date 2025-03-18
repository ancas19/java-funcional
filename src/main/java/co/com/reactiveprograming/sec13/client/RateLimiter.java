package co.com.reactiveprograming.sec13.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter {

    private static final Map<String,Integer> categoryAttempts= Collections.synchronizedMap(new HashMap<>());

    static {
       refresh();
    }

    static  <T> Mono<T> limitCalls(){
        return Mono.deferContextual(ctx->{
            var canAllow=ctx.<String>getOrEmpty("category")
                    .map(RateLimiter::canAllow)
                    .orElse(false);
            return canAllow ? Mono.empty() : Mono.error(new RuntimeException("Too many requests"));
        });
    }
    private static boolean canAllow(String category){
        Integer attempts=categoryAttempts.getOrDefault(category,0);
        if(attempts>0){
            categoryAttempts.put(category,attempts-1);
            return true;
        }
        return false;
    }


    private static  void refresh(){
        Flux.interval(Duration.ofSeconds(5))
                .startWith(0L)
                .subscribe(i->{
                    categoryAttempts.put("Standard",2);
                    categoryAttempts.put("Prime",3);
                });
    }
}
