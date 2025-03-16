package co.com.reactiveprograming.sec04;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec04.assignment.FileReadService;
import co.com.reactiveprograming.sec04.assignment.FileReadServiceImpl;
import reactor.core.publisher.Flux;

import java.nio.file.Path;

public class Lec09Assignment {

    public static void main(String[] args) {
        Path path= Path.of("src/main/resources/sec04/file.txt");
        FileReadService fileReadService = new FileReadServiceImpl();
        Flux<String> flux = fileReadService.read(path);
        flux.takeUntil(line->line.equalsIgnoreCase("line600"))
                .subscribe(Util.subscriber());
    }
}
