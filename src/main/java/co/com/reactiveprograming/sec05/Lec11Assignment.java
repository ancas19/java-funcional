package co.com.reactiveprograming.sec05;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec05.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

/*
Ensure that the external service is up and running
 */
public class Lec11Assignment {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .filter(i -> i > 5)
                .take(2)
                .next()
                .subscribe(System.out::println);

        ExternalServiceClient client = new ExternalServiceClient();
        for (int i = 1; i < 5; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(5);
    }
}
