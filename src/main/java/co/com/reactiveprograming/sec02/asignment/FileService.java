package co.com.reactiveprograming.sec02.asignment;

import reactor.core.publisher.Mono;

import java.io.IOException;

public interface FileService {
    Mono<String> getFile(String fileName);
    Mono<Void> writeFile(String fileName, String content);
    Mono<Void> deleteFile(String fileName);
}
