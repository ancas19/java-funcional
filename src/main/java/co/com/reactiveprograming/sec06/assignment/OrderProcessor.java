package co.com.reactiveprograming.sec06.assignment;

import reactor.core.publisher.Flux;

public interface OrderProcessor {
    void consume(Order order);
    Flux<String> stream();
}
