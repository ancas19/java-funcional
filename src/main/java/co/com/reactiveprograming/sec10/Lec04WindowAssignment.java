package co.com.reactiveprograming.sec10;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec10.assignment.window.FileWriter;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec04WindowAssignment {
    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);
        String fileName="src/main/resources/sec10/file%d.txt";

        eventStream()
                .window(Duration.ofMillis(1000))
                        .flatMap(i-> FileWriter.create(i, Path.of(fileName.formatted(count.getAndIncrement()))))
                .subscribe();

        Util.sleepSeconds(30);
    }

    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i->"event-"+(i+1));
    }
}
