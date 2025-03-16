package co.com.reactiveprograming.sec02;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

/**
    To delay the pusblisher creation
 */
public class Lec10MonoDefer {
    private static final Logger log= LoggerFactory.getLogger(Lec10MonoDefer.class);

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Mono<Integer> mono =Mono.defer(()->createPublisher(list));
        //createPublisher(list);
        mono.subscribe(Util.subscriber());
    }

    private static  Mono<Integer> createPublisher(List<Integer> list){
        log.info("Creating publisher");
        Util.sleepSeconds(1);
        return Mono.fromSupplier(() -> sum(list));
    }

    //Time consuming business logic
    private static int sum(List<Integer> list) {
        log.info("sum: of {}",list);
        Util.sleepSeconds(3);
        return list.stream().mapToInt(a->a).sum();
    }
}
