package co.com.reactiveprograming.sec09;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec09.assignment.ExternalServiceClient;

/*
Ensure that the external service is up and running
 */
public class Lec08ZipAssignment {
    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();

        for (int i = 1; i <= 10; i++) {
            client.getProduct(i)
                    .subscribe(Util.subscriber("sam"));
        }


        Util.sleepSeconds(5);
    }
}
