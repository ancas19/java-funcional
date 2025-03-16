package co.com.reactiveprograming.sec11.client;

public class ClientError  extends RuntimeException{
    ClientError(){
        super("Client error");
    }
}
