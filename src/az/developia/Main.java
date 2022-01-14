package az.developia;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var storage = new StorageImpl<Employee>(new File("a.txt"));
        var service = new EmployeeService(storage);
        service.save(new Employee(1L,"Ben", "It" ));
    }

    public static void testFileReading() {
        var file = new File("a.txt");

        try (var in = new BufferedInputStream(new FileInputStream(file), 2)) {
            while (in.available() > 0) {
                var b = new byte[in.available()];
                var read = in.read(b);
                System.out.println(read);

                System.out.println(new String(b));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
