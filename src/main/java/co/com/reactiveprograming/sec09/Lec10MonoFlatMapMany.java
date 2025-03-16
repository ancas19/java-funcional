package co.com.reactiveprograming.sec09;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec09.applications.OrderService;
import co.com.reactiveprograming.sec09.applications.UserService;

public class Lec10MonoFlatMapMany {
    public static void main(String[] args) {

        UserService.getUserId("mike")
                .flatMapMany(id -> OrderService.getOrders(id))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
