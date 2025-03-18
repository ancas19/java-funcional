package co.com.reactiveprograming.sec13;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec13.client.ExternalServiceClient;
import reactor.util.context.Context;

public class Lec04ContextRateLimiterDemo {
    public static void main(String[] args) {
        ExternalServiceClient client=new ExternalServiceClient();
        for (int i = 0; i < 20; i++) {
            client.getBook()
                    .contextWrite(Context.of("user","Mike"))
                    .subscribe(Util.subscriber());

            Util.sleepSeconds(1);
        }

        Util.sleepSeconds(10);
    }
}
