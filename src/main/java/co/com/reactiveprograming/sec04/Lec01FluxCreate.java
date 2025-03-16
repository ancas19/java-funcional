package co.com.reactiveprograming.sec04;

import co.com.reactiveprograming.common.Util;
import reactor.core.publisher.Flux;

/*
    To create a flux & emit items programmatically
 */
public class Lec01FluxCreate {
    public static void main(String[] args) {
       /* Flux.create(fluxSink -> {
           for (int i=0;i<10;i++){
               fluxSink.next(Util.faker().country().name());
           }
            fluxSink.complete();
        }).subscribe(Util.subscriber());
*/

        Flux.create(fluxSink -> {
            String country;
            do{
                country=Util.faker().country().name();
                fluxSink.next(country);
            }while(!country.equals("Canada"));

            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }


}
