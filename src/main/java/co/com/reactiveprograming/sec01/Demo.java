package co.com.reactiveprograming.sec01;

import co.com.reactiveprograming.sec01.publisher.PublisherImpl;
import co.com.reactiveprograming.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.time.Duration;

/**
 * 1. publisher does not produce data unless subscriber request for it.
 * 2. publisher will produce only <= subscriber request itmes. Publisher can also produce 0 items.
 * 3- subscriber can cancel the subscription. Producer should sto´at that moment as subscriber is no longer
 * interested in consuming the data.
 * 4. Producer can send the error signal to indicate something is wrong

 */
public class Demo {
    public static  void main(String[] args) throws InterruptedException {
        //demo1();
        //demo2();
        //demo3();
        demo4();
    }

    private static  void  demo1(){
        Publisher publisher = new PublisherImpl();
        Subscriber subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

    private static  void  demo2() throws InterruptedException {
        PublisherImpl publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
    }

    public static void demo3() throws InterruptedException {
        PublisherImpl publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }

    public static void demo4() throws InterruptedException {
        PublisherImpl publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(11);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }
}
