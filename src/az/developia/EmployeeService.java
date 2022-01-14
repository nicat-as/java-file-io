package az.developia;

public class EmployeeService {
    private Storage<Employee> storage;

    public EmployeeService(Storage<Employee> storage) {
        this.storage = storage;
    }

    public Employee save(Employee e) {
        this.storage.write(e);
        return e;
    }
}
