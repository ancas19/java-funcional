package co.com.reactiveprograming.sec02;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/*
If you have a CompletableFuture already, then we can convert that into a Mono
 */
public class Lec08MonoFromFuture {
    private static  final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) {
        Mono.fromFuture(()->getName());
               // .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }

   private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(()->{
            log.info("Generating name");
            return Util.faker().name().firstName();
        });
   }



}
