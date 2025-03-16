package co.com.reactiveprograming.sec09;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec09.helper.Kayak;

import java.time.Duration;

public class Lec06MergeUseCase {
    public static void main(String[] args) {
        Kayak.getFlights()
                .subscribe(Util.subscriber());


        Util.sleepSeconds(5);
    }
}
