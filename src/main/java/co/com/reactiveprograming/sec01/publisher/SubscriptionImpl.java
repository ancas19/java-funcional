package co.com.reactiveprograming.sec01.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {
    private static final Logger log= LoggerFactory.getLogger(SubscriptionImpl.class);
    private final int MAX_EMAILS=10;
    private final Faker faker;
    private final Subscriber<? super String> subscriber;
    private boolean cancelled;
    private int count;

    public SubscriptionImpl(Subscriber<? super String> s) {
        this.subscriber=s;
        this.faker=Faker.instance();
    }
    @Override
    public void request(long n) {
        if(cancelled) {
            return;
        }
        log.info("Subscription requested : {}",n);
        if(n>MAX_EMAILS){
            subscriber.onError(new RuntimeException("too many emails requested"));
            cancelled=true;
            return;
        }
        for (int i = 0; i< n && count<MAX_EMAILS; i++) {
            count++;
            subscriber.onNext(this.faker.internet().emailAddress());
        }
        if (count==MAX_EMAILS){
            log.info("no more data to send");
            subscriber.onComplete();
            cancelled=true;
        }
    }

    @Override
    public void cancel() {
        log.info("subscriber  has cancelled");
        this.cancelled=true;
    }
}
