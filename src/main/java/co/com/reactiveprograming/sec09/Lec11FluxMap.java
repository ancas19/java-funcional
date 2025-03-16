package co.com.reactiveprograming.sec09;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec09.applications.OrderService;
import co.com.reactiveprograming.sec09.applications.User;
import co.com.reactiveprograming.sec09.applications.UserService;

public class Lec11FluxMap {

    public static void main(String[] args) {
        /*
        Get all the orders from order service
         */

        UserService.getUsers()
                .map(User::id)
                .flatMap(OrderService::getOrders,1)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
