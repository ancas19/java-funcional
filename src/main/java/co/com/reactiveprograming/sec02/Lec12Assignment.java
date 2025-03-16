package co.com.reactiveprograming.sec02;

import co.com.reactiveprograming.common.Util;
import co.com.reactiveprograming.sec02.asignment.FileService;
import co.com.reactiveprograming.sec02.asignment.FileServiceImp;

public class Lec12Assignment {
    public static void main(String[] args) {
        FileService fileService = new FileServiceImp();

        fileService.writeFile("file.txt", "line1")
                .subscribe(Util.subscriber());

        fileService.getFile("file.txt")
                .subscribe(Util.subscriber());

        fileService.deleteFile("file.txt");

    }
}
