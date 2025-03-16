package co.com.reactiveprograming.common;


import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

public abstract class AbstractHttpClass {
    public static final String URL = "http://localhost:7070";
    protected  final HttpClient client;

    public AbstractHttpClass() {
        LoopResources loopResources= LoopResources.create("ancas",1,true);
        this.client = HttpClient.create().runOn(loopResources).baseUrl(URL);
    }
}
