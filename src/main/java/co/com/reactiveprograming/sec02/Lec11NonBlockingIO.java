package co.com.reactiveprograming.sec02;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * To demo non-blocking IO
 * Ensure that the external service is up and running
 */
public class Lec11NonBlockingIO {
    public static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {
       ExternalServiceClient client = new ExternalServiceClient();

       for (int i = 1; i <= 100; i++) {
           client.getName(i)
                   .subscribe(Util.subscriber());
       }

       Util.sleepSeconds(5);
    }
}
