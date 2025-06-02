package com.example.EmployeeManagementSystem.serviceImpli;

import com.example.EmployeeManagementSystem.Repository.ClientRepository;
import com.example.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.Repository.TaskRepository;
import com.example.EmployeeManagementSystem.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.entity.Task;
import com.example.EmployeeManagementSystem.exceptionHandler.CatageoryAlreadyExistException;
import com.example.EmployeeManagementSystem.exceptionHandler.ResourseNotFoundException;
import com.example.EmployeeManagementSystem.mapper.EmployeeMapper;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpli implements EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private TaskRepository taskRepo;
    @Autowired
    private ClientRepository clientRepo;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {

        Optional<Employee> empNameFind = empRepo.findByName(dto.getName());
        if(empNameFind.isPresent())
        {
         //   throw  new RuntimeException("Employee Name Already Exist");          // before implimenting  CustomException

            throw  new CatageoryAlreadyExistException("Employee Name = " + dto.getName() +  " Already Exist !");
        }

        Employee employee = employeeMapper.dtoToEntity(dto);
        return employeeMapper.entityToDto(empRepo.save(employee));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return empRepo.findAll().stream().map(employeeMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return employeeMapper.entityToDto(empRepo.findById(id).orElseThrow(()->new  ResourseNotFoundException("! employee not Found Id: "+id)));
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {

        Employee emp = empRepo.findById(id).orElseThrow(()->new  ResourseNotFoundException("! Employee not found ID: "+id));

        // Do NOT change description, startDate, endDate,email or projectId (they remain as it is) . in " if( ) block  " those value set want to update other field unchange
         /* task_id is " FK column" task_id change assign from task class . from task class assign task to employee & task_id auto store in
                 in employee table
          */
       if(dto.getPhone()!=null) {
            emp.setPhone(dto.getPhone());
        }
       if(dto.getSalary()!= null){
           emp.setSalary( dto.getSalary() );
       }

       //clientId is Fk and we update them.
        if(dto.getClientId() !=null)
        {
           emp.setClient( clientRepo.findById(dto.getClientId()).orElseThrow(()->
                               new  ResourseNotFoundException("! client not Found ID: "+ dto.getClientId())) );
        }

        if(dto.getTaskId()!= null){
         Task taskAvailable  = taskRepo.findById(dto.getTaskId()).
                        orElseThrow(()->new  ResourseNotFoundException("! task not Found: "+dto.getTaskId())) ;

            emp.setTask(taskAvailable);

        }
        return employeeMapper.entityToDto(empRepo.save(emp));
    }

    @Override
    public void deleteEmployee(Long id) {

        empRepo.findById(id).orElseThrow(()-> new ResourseNotFoundException("! employee Not Found ID: "+ id));

        empRepo.deleteById(id);
    }
}
