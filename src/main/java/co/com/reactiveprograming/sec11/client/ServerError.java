package co.com.reactiveprograming.sec11.client;

public class ServerError  extends RuntimeException{
    ServerError(){
        super("Server error");
    }
}
