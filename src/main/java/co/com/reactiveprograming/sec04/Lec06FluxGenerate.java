package co.com.reactiveprograming.sec04;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
Flux Generate
- Invoke the given lambda expression again and again based on downstream demand.
- We can emit only one value at a time
- Will stop when complete metod is invoked
- Will stop when error method is invoked
- Will stop downstream cancels

 */
public class Lec06FluxGenerate {
    private static final Logger log= LoggerFactory.getLogger(Lec06FluxGenerate.class);
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            log.info("Invoke");
            synchronousSink.next(1);
            synchronousSink.complete();
        })
                .take(4)
                .subscribe(Util.subscriber());
    }
}
