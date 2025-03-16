package co.com.reactiveprograming.sec10.assignment.buffer;

import co.com.reactiveprograming.common.Util;
import com.github.javafaker.Book;

public record BookOrder(
        String genre,
        String title,
        Integer price
) {

    public static BookOrder creae(){
        Book book = Util.faker().book();
        return new BookOrder(book.genre(),book.title(),Util.faker().random().nextInt(10,100));
    }
}
