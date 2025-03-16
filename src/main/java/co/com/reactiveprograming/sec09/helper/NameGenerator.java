package co.com.reactiveprograming.sec09.helper;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class NameGenerator {
    private static final Logger log= LoggerFactory.getLogger(NameGenerator.class);
    private final List<String> redis= new ArrayList<>();

    public Flux<String> generateNames(){
        return Flux.generate(sink->{
           log.info("Generating name");
            Util.sleep(Duration.ofSeconds(1));
            String name= Util.faker().name().fullName();
            redis.add(name);
            sink.next(name);
        }).startWith(redis)
                .cast(String.class);
    }
}
