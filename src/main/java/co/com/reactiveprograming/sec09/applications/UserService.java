package co.com.reactiveprograming.sec09.applications;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserService {
    private static final Map<String,Integer> usersTable= Map.of(
        "sam", 1,
        "mike", 2,
        "jake", 3
    );

    public static Flux<User> getUsers() {
        return Flux.fromIterable(usersTable.entrySet())
                .map(e -> new User(e.getValue(), e.getKey()));
    }

    public static Mono<Integer> getUserId(String name) {
        return Mono.fromSupplier(()->usersTable.get(name));
    }
}
