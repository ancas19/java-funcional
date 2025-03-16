package co.com.reactiveprograming.sec09.applications;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/*
    Just for demoo.
    Imagine order-service, as an application, has an endpoit.
    This is a client class which represents to call the endpoint.(Id request)

 */
public class OrderService {
    private static final Map<Integer, List<Order>> ordersTable= Map.of(
            1,List.of(
                    new Order(1, Util.faker().commerce().productName(), Util.faker().random().nextInt(10,100)),
                    new Order(1, Util.faker().commerce().productName(), Util.faker().random().nextInt(10,100))
            ),
            2,List.of(
                    new Order(2, Util.faker().commerce().productName(), Util.faker().random().nextInt(10,100)),
                    new Order(2, Util.faker().commerce().productName(), Util.faker().random().nextInt(10,100)),
                    new Order(2, Util.faker().commerce().productName(), Util.faker().random().nextInt(10,100))
            ),
            3,List.of()

    );

    public static Flux<Order> getOrders(Integer userId){
        return Flux.fromIterable(ordersTable.get(userId))
                .delayElements(Duration.ofMillis(500))
                .transform(Util.fluxLogger("order for user: " + userId));
    }
}
