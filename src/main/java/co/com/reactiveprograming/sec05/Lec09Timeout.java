package co.com.reactiveprograming.sec05;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Timeout - will produce timeout error,
 * - we can handle as part of onError methods
 * there is also and overloaded  method to accept a publisher
 * we can have multiple timeouts. The closest one to subscriber will take effect for the subscriber.

 */
public class Lec09Timeout {
    public static void main(String[] args) {
        Mono<String> productName = getProductname()
                .timeout(Duration.ofSeconds(1),fallback());

        productName
                .timeout(Duration.ofMillis(5000))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

    private static Mono<String> getProductname(){
        return Mono.fromSupplier(()-> "Service-"+Util.faker().commerce().productName())
                .delayElement(Duration.ofSeconds(3));
    }

    private static Mono<String> fallback(){
        return Mono.fromSupplier(()-> "fallback-"+Util.faker().commerce().productName())
                .delayElement(Duration.ofSeconds(2))
                .doFirst(()-> System.out.println("doFirst"));
    }
}
