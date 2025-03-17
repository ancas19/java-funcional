package co.com.reactiveprograming.sec12;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/*
We can emit multiple messages, but there will be only one subscriber.
 */
public class Lec02SinkUnicast {
    public static void main(String[] args) {
        demo1();
    }

    private static  void demo1(){
        // Handle through which we would push items
        // onBackPressureBuffer - unbounded queque
      Sinks.Many<Object> sink= Sinks.many().unicast().onBackpressureBuffer();

     //Handle through which subscribers will receive items
      Flux<Object>flux=sink.asFlux();


      for (int i = 0; i < 10; i++) {
          sink.tryEmitNext(i);
      }
      flux.subscribe(Util.subscriber());
      flux.subscribe(Util.subscriber("Two"));
    }
}
