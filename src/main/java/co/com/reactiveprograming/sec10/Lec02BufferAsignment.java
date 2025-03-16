package co.com.reactiveprograming.sec10;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec10.assignment.buffer.BookOrder;
import co.com.reactiveprograming.sec10.assignment.buffer.RevenueReport;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Lec02BufferAsignment {

    public static void main(String[] args) {
        Set<String> allowedCategories=Set.of(
                "Science fiction",
                "Fantasy",
                "Suspense/Thriller"
        );

        orderStream().filter(o->allowedCategories.contains(o.genre()))
                .buffer(Duration.ofSeconds(5))
                .map(Lec02BufferAsignment::generateReport)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(15);
    }

    private static Flux<BookOrder> orderStream(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i->BookOrder.creae());
    }

    private static RevenueReport generateReport(List<BookOrder> orders){
        Map<String, Integer> revenue = orders.stream()
                .collect(Collectors.groupingBy(
                        BookOrder::genre,
                        Collectors.summingInt(BookOrder::price)
                ));
        return new RevenueReport(LocalTime.now(), revenue);
    }
}
