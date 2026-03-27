public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public boolean terminateEmployee(Employee employee){
        System.out.println("Terminating Employee");
        return true;
    }

}
