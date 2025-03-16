package co.com.reactiveprograming.sec04.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FileReadServiceImpl implements FileReadService{
    private static final Logger log= LoggerFactory.getLogger(FileReadServiceImpl.class);

    @Override
    public Flux<String> read(Path path) {
        return Flux.generate(
                ()->openFile(path),
                this::readFile,
                this::closeFile
        );
    }

    private BufferedReader openFile(Path path) throws IOException {
        log.info("File opened: {}",path);
        return Files.newBufferedReader(path);
    }

    private BufferedReader readFile(BufferedReader reader, SynchronousSink<String> sink) {
        try {
            String line=reader.readLine();
            log.info("Read line: {}",line);
            if(Objects.isNull(line)){
                sink.complete();
                return reader;
            }
            sink.next(line);
            return reader;

        } catch (IOException e) {
            sink.error(e);
            return reader;
        }
    }

    private void closeFile(BufferedReader reader){
        try {
            reader.close();
            log.info("File closed");
        } catch (IOException e) {
            log.error("Error closing file",e);
        }
    }
}
