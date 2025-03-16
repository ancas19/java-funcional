package co.com.reactiveprograming.sec03.helper;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

public class NamesGenerator {
    public static List<String> getNamesList(Integer count){
        return IntStream.rangeClosed(1, count)
                .mapToObj(i->generateName())
                .toList();
    }

    public static Flux<String> getNamesFlux(Integer count){
        return Flux.range(1, count)
                .map(i->generateName());
    }
    private static String generateName() {
        Util.sleepSeconds(1);
        return Util.faker().name().firstName();
    }
}
