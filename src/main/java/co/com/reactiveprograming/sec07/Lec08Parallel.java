package co.com.reactiveprograming.sec07;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec08Parallel {
    private static final Logger log = LoggerFactory.getLogger(Lec08Parallel.class);
    public static void main(String[] args) {
        Flux.range(1,10)
                .parallel(3)
                .runOn(Schedulers.parallel())
                .map(i-> process(i))
                .sequential()
                .map(i->i +"a")
                .subscribe(Util.subscriber("sub1"));

        Util.sleepSeconds(10);
    }

    private static  int process(int i){
        log.info("Time consuming task");
        Util.sleepSeconds(1);
        return i*2;
    }
}
