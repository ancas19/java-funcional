package co.com.reactiveprograming.sec03;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class Lec03FluxFromIterableOrArray {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        Flux.fromIterable(list)
                .subscribe(Util.subscriber("Iterable"));
        Integer[] array = new Integer[]{1,2,3};
        Flux.fromArray(array)
                .subscribe(Util.subscriber("array"));
    }
}
