package co.com.reactiveprograming.sec09;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec09.assignment.ExternalServiceClient;
import reactor.core.publisher.Flux;

/*
Ensure that the external service is up and running
 */
public class Lec13ConcatMap {
    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();
        Flux.range(1,10)
                .concatMap(i -> client.getProduct(i),3)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
