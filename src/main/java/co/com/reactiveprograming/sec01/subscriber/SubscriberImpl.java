package co.com.reactiveprograming.sec01.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriberImpl implements Subscriber<String> {
    private static final Logger log= LoggerFactory.getLogger(SubscriberImpl.class);
    private  Subscription subscription;

    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public void onSubscribe(Subscription s) {
        this.subscription=s;
    }

    @Override
    public void onNext(String email) {
        log.info("Email received: {}",email);
    }

    @Override
    public void onError(Throwable t) {
        log.info("Error received: {}",t);
    }

    @Override
    public void onComplete() {
        log.info("Complete received");
    }
}
