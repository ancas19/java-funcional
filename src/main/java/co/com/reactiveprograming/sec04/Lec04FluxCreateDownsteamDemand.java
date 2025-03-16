package co.com.reactiveprograming.sec04;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
    Flux create does not check the downstream demand by default! It is by design.
 */
public class Lec04FluxCreateDownsteamDemand {
    private static final Logger log= LoggerFactory.getLogger(Lec04FluxCreateDownsteamDemand.class);

    public static void main(String[] args) {
        SubscriberImpl subscriber = new SubscriberImpl();
        Flux.<String>create(fluxsink->{
           for(int i=0; i<10;i++){
               String name = Util.faker().name().firstName();
               log.info("Generated name: {}",name);
               fluxsink.next(name);
           }
           fluxsink.complete();
        }).subscribe(subscriber);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(3);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(3);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);

    }

    private static void produceOnDemnd(){
        SubscriberImpl subscriber = new SubscriberImpl();
        Flux.<String>create(fluxsink->{
          fluxsink.onRequest(request -> {
              for(int i=0; i<request && !fluxsink.isCancelled();i++){
                  String name = Util.faker().name().firstName();
                  log.info("Generated name: {}",name);
                  fluxsink.next(name);
              }
          });

        }).subscribe(subscriber);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(3);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(3);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);
    }
}
