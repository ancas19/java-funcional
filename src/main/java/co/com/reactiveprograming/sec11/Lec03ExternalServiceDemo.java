package co.com.reactiveprograming.sec11;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec11.client.ExternalServiceClient;
import co.com.reactiveprograming.sec11.client.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.util.retry.Retry;

import java.time.Duration;

/*
Ensure that the external service is up and running
 */
public class Lec03ExternalServiceDemo {
    private static final Logger log= LoggerFactory.getLogger(Lec03ExternalServiceDemo.class);

    public static void main(String[] args) {


        retry();
        Util.sleepSeconds(60);
    }

    /*
    This is just demo. Do not bombard  the remote sever indefinitely w/o any delay
     */

    private static void repeat(){
        ExternalServiceClient client  = new ExternalServiceClient();
        client.getCountry()
                .repeat()
                .takeUntil(c->c.equalsIgnoreCase("Colombia"))
                .subscribe(Util.subscriber());
    }


    /*
    This is just demo. Do not bombard  the remote sever indefinitely w/o any delay
     */
    private static void retry(){
        ExternalServiceClient client  = new ExternalServiceClient();
        client.getName(2)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber());
    }

    private static Retry retryOnServerError(){
        return Retry.fixedDelay(20, Duration.ofSeconds(1))
                .filter(ex -> ServerError.class.equals(ex.getClass()))
                .doBeforeRetry(rs -> log.info("retrying {}", rs.failure().getMessage()));
    }
}
