package co.com.reactiveprograming.sec02;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

/*
 To delay the execution using  supplier / callable
 */
public class Lec05MonoFromSupplier {
    private static final Logger log= LoggerFactory.getLogger(Lec05MonoFromSupplier.class);

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Mono.fromSupplier(() -> sum(list))
                .subscribe(Util.subscriber());
    }


    private static int sum(List<Integer> list) {
        log.info("sum: of {}",list);
        return list.stream().mapToInt(a->a).sum();
    }
}
