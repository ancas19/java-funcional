package co.com.reactiveprograming.sec04;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

/*
Take is similar to java stream's limit
 */
public class Lec05TakeOperator {
    public static void main(String[] args) {
        takenUntil();
    }

    private static  void taken(){
        Flux.range(0, 10)
                .log("take")
                .take(30)
                .log("subscribe")
                .subscribe(Util.subscriber());
    }


    private static  void takenWhile(){
        Flux.range(0, 10)
                .log("take")
                .takeWhile(i->i<5) // stop when the condition is not met
                .log("subscribe")
                .subscribe(Util.subscriber());
    }

    private static  void takenUntil(){
        Flux.range(0, 10)
                .log("take")
                .takeUntil(i->i<5) // stop when the condition is  met + allow the last item
                .log("subscribe")
                .subscribe(Util.subscriber());
    }
}
