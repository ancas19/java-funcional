package co.com.reactiveprograming.sec07;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec07.client.ExternalServiceClient;
import reactor.core.scheduler.Schedulers;

public class Lec06EventLoopIssueFix {
    public static void main(String[] args){
        ExternalServiceClient client = new ExternalServiceClient();
        for (int i = 1; i <=5; i++) {
            client.getName(i)
                    .map(res->process(res))
                    .subscribe(Util.subscriber());
        }
        Util.sleepSeconds(20);
    }

    private static String process(String name){
        Util.sleepSeconds(1);
        return name+"-processed";
    }
}
