package co.com.reactiveprograming.sec09;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec09.applications.*;
import reactor.core.publisher.Mono;

import java.util.List;

/*
Get all users and build 1 object as shown here.
Record UserInformation(Integer userId, String username, Integer balance, List<Order> orders)
 */
public class Lec16Assignment {
    record UserInformation(Integer userId, String username, Integer balance, List<Order> orders){}

    public static void main(String[] args) {
        UserService.getUsers()
                .flatMap(Lec16Assignment::getUserInformation)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);

    }

    private static Mono<UserInformation> getUserInformation(User user){
        return Mono.zip(
                PaymentService.getUserBalance(user.id()),
                OrderService.getOrders(user.id()).collectList()
        ).map(tuple -> new UserInformation(
                user.id(),
                user.username(),
                tuple.getT1(),
                tuple.getT2()
        ));
    }

}
