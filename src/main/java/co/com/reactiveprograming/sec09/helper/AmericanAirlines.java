package co.com.reactiveprograming.sec09.helper;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

//To represent the client  class to call remote service
public class AmericanAirlines {
    private static final String AIRLINE="AmericanAirlines";

    public static Flux<Flight> getFlights(){
        return Flux.range(1, Util.faker().random().nextInt(5,10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(400,900)))
                .map(i->new Flight(AIRLINE,Util.faker().random().nextInt(300,900)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
