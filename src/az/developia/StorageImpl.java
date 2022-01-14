package az.developia;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StorageImpl<T> implements Storage<T> {
    private File file;

    public StorageImpl(File file) {
        this.file = file;
    }

    @Override
    public List<T> readAll() {
        return null;
    }

    @Override
    public void writeAll(List<T> dataList) {
        dataList.forEach(this::write);
    }

    @Override
    public void write(T data) {
        var list = Arrays.stream(data.getClass().getMethods())
                .filter(method -> method.getName().startsWith("get"))
                .map(method -> {
                    try {
                        return method.invoke(data).toString();
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        var styled = doStyle(list);
        writeToFile(styled, this.file, true);
    }
}
