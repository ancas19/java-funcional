package co.com.reactiveprograming.sec13;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/*
Context is for providing metadata about the request (Similar to HTTP Headers)
 */
public class Lec01Context {
    private static final Logger log= LoggerFactory.getLogger(Lec01Context.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(Context.of("user1","Andrés"))
                .subscribe(Util.subscriber());
    }


    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx->{
            if(ctx.hasKey("user")){
                return Mono.just("Welcome "+ctx.get("user"));
            }
            return Mono.error(new RuntimeException("Unauthorized"));
        });
    }
}
