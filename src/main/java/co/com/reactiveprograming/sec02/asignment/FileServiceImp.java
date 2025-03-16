package co.com.reactiveprograming.sec02.asignment;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class FileServiceImp implements FileService {
    private static final Logger log = Logger.getLogger(FileServiceImp.class.getName());
    private final Path PATH = Path.of("src/main/resources/sec02/");
    @Override
    public Mono<String> getFile(String fileName) {
        return  Mono.fromCallable(()->Files.readString(PATH.resolve(fileName)));
    }

    @Override
    public Mono<Void> writeFile(String fileName, String content) {
        return Mono.fromRunnable(() ->write(fileName, content));
    }

    @Override
    public Mono<Void> deleteFile(String fileName) {
        return Mono.fromRunnable(() -> delete(fileName));
    }

    private void write(String fileName, String content) {
        try {
            Files.writeString(PATH.resolve(fileName), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(String fileName) {
        try {
            Files.delete(PATH.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
