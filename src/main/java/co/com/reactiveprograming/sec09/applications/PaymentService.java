package co.com.reactiveprograming.sec09.applications;

import reactor.core.publisher.Mono;

import java.util.Map;

/*
Just for demo.
Imagine payment-service, as an application, has an endpoit.
This is a client class which represents to call the endpoint.
 */
public class PaymentService {
    private static final Map<Integer, Integer> paymentsTable= Map.of(
            1,100,
            2,200,
            3,300
    );

    public static Mono<Integer> getUserBalance(Integer userId){
        return Mono.fromSupplier(()->paymentsTable.get(userId));
    }
}
