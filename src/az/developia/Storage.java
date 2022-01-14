package az.developia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.SplittableRandom;
import java.util.StringJoiner;

public interface Storage<T> {
    List<T> readAll();

    void writeAll(List<T> dataList);

    void write(T data);

    default String doStyle(List<String> dataList) {
        var sj = new StringJoiner("|");
        dataList.forEach(sj::add);
        return sj.toString();
    }

    default void writeToFile(String data, File file, boolean canAppend) {
        try (var os = new FileOutputStream(file, canAppend)) {
            os.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
