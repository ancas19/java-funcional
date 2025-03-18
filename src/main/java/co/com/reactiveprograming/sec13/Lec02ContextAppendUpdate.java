package co.com.reactiveprograming.sec13;

import co.com.reactiveprograming.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/*
Context is an inmutable map. We can append additional info!
 */
public class Lec02ContextAppendUpdate {
    private static final Logger log= LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(ctx->ctx.put("user",ctx.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("a","b").put("c","d").put("e","f"))
                .contextWrite(Context.of("user","Andrés"))
                .subscribe(Util.subscriber());
    }


    private static void append(){
        getWelcomeMessage()
                .contextWrite(Context.of("a","b").put("c","d").put("e","f"))
                .contextWrite(Context.of("user","Andrés"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx->{
            log.info("{}",ctx);
            if(ctx.hasKey("user")){
                return Mono.just("Welcome "+ctx.get("user"));
            }
            return Mono.error(new RuntimeException("Unauthorized"));
        });
    }
}
