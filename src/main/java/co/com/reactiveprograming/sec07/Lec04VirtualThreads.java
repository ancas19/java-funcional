package co.com.reactiveprograming.sec07;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Reactor supports virtual threads
 * Sysmte.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads",  "true"");

 */
public class Lec04VirtualThreads {
    private static final Logger log = LoggerFactory.getLogger(Lec04VirtualThreads.class);

    public static void main(String[] args) {
        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads",  "true");
        Flux flux= Flux.create(sink->{
                    for (int i=0;i<10;i++){
                        log.info("flux send {}",i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(v-> log.info("value: {}",v))
                .doFirst(()-> log.info("doFirst-1{}",Thread.currentThread().isVirtual()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(()-> log.info("doFirst 2"));

        Runnable runnable1=()-> flux.subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable1);

        Util.sleepSeconds(5);
    }
}
