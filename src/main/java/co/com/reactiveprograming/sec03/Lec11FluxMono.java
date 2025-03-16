package co.com.reactiveprograming.sec03;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
To covert a Flux to Mono
 */
public class Lec11FluxMono {
    public static void main(String[] args) {
        Flux<Integer>flux=Flux.range(1, 10);
             flux.next()
                 .subscribe(Util.subscriber());
    }

    private static Mono<String> getNames(Integer userID){
        return switch (userID) {
            case 1 -> Mono.just("John");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Error"));
        };
    }

    private static void save(Flux<String> flux){
        flux.subscribe(Util.subscriber());
    }

    private static void monoToFlux(Integer userId){
        Mono<String> mono = getNames(userId);
        save(Flux.from(mono));
    }
}
