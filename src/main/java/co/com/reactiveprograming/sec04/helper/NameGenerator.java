package co.com.reactiveprograming.sec04.helper;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGenerator implements Consumer<FluxSink<String>> {
    private FluxSink<String> fluxSink;
    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        System.out.println("got a flux sink");
        this.fluxSink=stringFluxSink;
    }

    public void generate(){
        this.fluxSink.next(Util.faker().name().firstName());
    }
}
