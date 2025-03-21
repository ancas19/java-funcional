package co.com.reactiveprograming.sec10.assignment.window;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriter {
    private final Path path;
    private BufferedWriter writer;

    public FileWriter(Path path){
        this.path=path;
    }

    private void createFile() {
        try {
            this.writer = Files.newBufferedWriter(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeFile(){
        try {
            this.writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeLine(String line){
        try {
            this.writer.write(line);
            this.writer.newLine();
            this.writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Mono<Void> create(Flux<String> flux,Path path){
        FileWriter writer=new FileWriter(path);
        return flux.doOnNext(writer::writeLine)
                .doFirst(writer::createFile)
                .doFinally(i->writer.closeFile())
                .then();
    }
}
