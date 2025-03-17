package co.com.reactiveprograming.sec12.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackRoom {
    private static final Logger log= LoggerFactory.getLogger(SlackRoom.class);
    private final String name;
    private final Sinks.Many<SlackMessage> sink;
    private final Flux<SlackMessage> flux;
    public SlackRoom(String name){
        this.name=name;
        this.sink=Sinks.many().replay().all();
        flux=sink.asFlux();
    }

    public void addMember(SlackMember member){
        log.info("{} joined the room",member.getName());
        this.subscribeToRoomMessages(member);
        member.setMessageConsumer(msg->postMessage(msg,member.getName()));
    }

    private void subscribeToRoomMessages(SlackMember member){
        this.flux.filter(sm->!sm.sender().equals(member.getName()))
                .map(sm->sm.formatForDelivery(member.getName()))
                .subscribe(member::receives);
    }

    private void postMessage(String message,String sender){
        this.sink.tryEmitNext(new SlackMessage(sender,message));
    }
}
