package co.com.reactiveprograming.sec03;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec03.client.ExternalServiceClient;

/*
    To demo no-blocking IO with streaming messages
    Ensure that the external service is up and running
 */
public class Lec08NoBlockingStreamingMessages {
    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();
        client.getNames()
                .subscribe(Util.subscriber("sub1"));

        client.getNames()
                .subscribe(Util.subscriber("sub2"));
        Util.sleepSeconds(10);
    }
}
