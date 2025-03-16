package co.com.reactiveprograming.sec05;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.UnaryOperator;

public class Lec10Transform {
    private static final Logger log= LoggerFactory.getLogger(Lec10Transform.class);
    record Customer(int id, String name){}
    record PurchaseOrder(String productName, int price, int quantity){}
    public static void main(String[] args) {

        getCostumers()
                .transform(addDebbuger())
                .subscribe();

        getPurchaseOrders()
                .transform(addDebbuger())
                .subscribe();
    }

    private static Flux<Customer> getCostumers(){
        return Flux.range(1,3)
                .map(i->new Customer(i, Util.faker().name().firstName()));
    }

    private static Flux<PurchaseOrder> getPurchaseOrders(){
        return Flux.range(1,3)
                .map(i->new PurchaseOrder(Util.faker().commerce().productName(), Util.faker().number().numberBetween(1,100), Util.faker().number().numberBetween(1,10)));
    }

    private static <T>UnaryOperator<Flux<T>> addDebbuger(){
        return flux -> flux
                .doOnNext(i->log.info("customer: {}",i))
                .doOnComplete(()->log.info("completed"))
                .doOnError(e->log.info("error: {}",e));
    }
}
