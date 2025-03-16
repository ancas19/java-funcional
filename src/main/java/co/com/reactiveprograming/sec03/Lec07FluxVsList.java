package co.com.reactiveprograming.sec03;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec01.subscriber.SubscriberImpl;
import co.com.reactiveprograming.sec03.helper.NamesGenerator;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec07FluxVsList {
    public static void main(String[] args) {

       /* List<String> namesList = NamesGenerator.getNamesList(10);
        System.out.println(namesList);*/

        SubscriberImpl subscriber =new SubscriberImpl();
        Flux<String> namesFlux = NamesGenerator.getNamesFlux(10);
        namesFlux.subscribe(subscriber);
        subscriber.getSubscription().request(5);
        subscriber.getSubscription().cancel();
    }
}
