package co.com.reactiveprograming.sec05;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec05Subscribe {
    private static final Logger log = LoggerFactory.getLogger(Lec05Subscribe.class);

    public static void main(String[] args) {
        Flux.range(1,10)
                .doOnNext(item -> log.info("onNext: {}",item))
                .doOnComplete(() -> log.info("onComplete"))
                .doOnError(error -> log.info("onError: {}",error))
                .subscribe(Util.subscriber());
    }
}
