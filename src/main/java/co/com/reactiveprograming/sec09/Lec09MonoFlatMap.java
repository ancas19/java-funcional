package co.com.reactiveprograming.sec09;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec09.applications.PaymentService;
import co.com.reactiveprograming.sec09.applications.UserService;

public class Lec09MonoFlatMap {
    public static void main(String[] args) {
        /*
        We have username
        Get user account balance
         */

        UserService.getUserId("sam")
                .flatMap(id -> PaymentService.getUserBalance(id))
                .subscribe(Util.subscriber());
    }
}
