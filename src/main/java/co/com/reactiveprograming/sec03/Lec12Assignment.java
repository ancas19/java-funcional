package co.com.reactiveprograming.sec03;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec03.assignment.StockPriceObserver;
import co.com.reactiveprograming.sec03.client.ExternalServiceClient;

public class Lec12Assignment {
    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();
        StockPriceObserver subscriber = new StockPriceObserver();
        client.getPrices()
                .subscribe(subscriber);

        Util.sleepSeconds(50);
    }
}
