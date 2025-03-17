package co.com.reactiveprograming.sec12;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec12.assignment.SlackMember;
import co.com.reactiveprograming.sec12.assignment.SlackRoom;

public class Lec08SlackAssignment {
    public static void main(String[] args) {
        SlackRoom room=new SlackRoom("Reactive Programming");
        SlackMember sam=new SlackMember("Sam");
        SlackMember mike=new SlackMember("Mike");
        SlackMember jake=new SlackMember("Jake");

        room.addMember(sam);
        room.addMember(mike);

        sam.says("hi");
        Util.sleepSeconds(4);

        mike.says("how are you");
        sam.says("fine");
        Util.sleepSeconds(4);

        room.addMember(jake);
        jake.says("hi guys. glad to be here");
    }
}
