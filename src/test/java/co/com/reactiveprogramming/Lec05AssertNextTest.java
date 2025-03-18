package co.com.reactiveprogramming;

import co.com.reactiveprograming.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    "assertNext" is a method in StepVerifier
    assertNext =  consumeNextWith
    We can also collect all the items and test.
 */
public class Lec05AssertNextTest {
    record Book (int id, String author, String title){}

    private Flux<Book> getBooks(){
        return Flux.range(1,3)
                .map(i->new Book(i, Util.faker().book().author(),Util.faker().book().author()));
    }

    @Test
    void assertNextTest(){
        StepVerifier.create(getBooks())
                .assertNext(book -> assertEquals(book.id(), 1))
                .thenConsumeWhile(book -> Objects.nonNull(book.title))
                .thenConsumeWhile(book -> Objects.nonNull(book.author))
                .expectComplete()
                .verify();
    }

    @Test
    void collectAllAndTest(){
        StepVerifier.create(getBooks().collectList())
                .assertNext(list -> assertEquals(list.size(), 3))
                .expectComplete()
                .verify();
    }
}
