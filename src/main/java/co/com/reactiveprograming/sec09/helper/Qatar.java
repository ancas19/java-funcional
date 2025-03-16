package co.com.reactiveprograming.sec09.helper;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

//To represent the client  class to call remote service
public class Qatar {
    private static final String AIRLINE="Qatar";

    public static Flux<Flight> getFlights(){
        return Flux.range(1, Util.faker().random().nextInt(3,5))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(300,800)))
                .map(i->new Flight(AIRLINE,Util.faker().random().nextInt(400,1200)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
