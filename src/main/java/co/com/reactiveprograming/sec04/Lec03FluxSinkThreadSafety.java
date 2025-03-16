package co.com.reactiveprograming.sec04;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Lec03FluxSinkThreadSafety {
    private static final Logger log= LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);
    public static void main(String[] args) {
        demo2();
    }

    private static  void demo1(){
        List<Integer> list= new ArrayList<>();
        Runnable runnable=()->{
                for (int i=0;i<1000;i++){
                    list.add(i);
                }
            };
        for (int i=0;i<10;i++){
            Thread.ofPlatform().start(runnable);
        }
        Util.sleepSeconds(10);
        log.info("list size: {}",list.size());
    }

    /*
    ArrayList is not thread safe
    Flux sink is thread safe. we get all the 1000 items safely into arrayList
     */
    private static  void demo2(){
        List<String> list= new ArrayList<>();
        NameGenerator nameGenerator = new NameGenerator();
        Flux<String> flux = Flux.create(nameGenerator);
        flux.subscribe(list::add);

        Runnable runnable=()->{
            for (int i=0;i<1000;i++){
                nameGenerator.generate();
            }
        };
        for (int i=0;i<10;i++){
            Thread.ofPlatform().start(runnable);
        }
        Util.sleepSeconds(10);
        log.info("list size: {}",list.size());
    }
}
