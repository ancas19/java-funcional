package co.com.reactiveprograming.sec05;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleUntilAssignment {
    public static void main(String[] args) {
        Flux.<String>generate(sink->sink.next(Util.faker().country().name()))
                .handle((item, sink) -> {
                    sink.next(item);
                    if(item.equalsIgnoreCase("Canada")) {
                        sink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }
}
