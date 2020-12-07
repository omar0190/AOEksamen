package com.ao.aoeksamenprojekt.service.position;

import com.ao.aoeksamenprojekt.model.Employee;
import com.ao.aoeksamenprojekt.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmployeeServiceJPA implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceJPA(EmployeeRepository employeeRepository) {this.employeeRepository = employeeRepository; }

    @Override
    public ArrayList<Employee> findAll(){
        ArrayList<Employee> list = new ArrayList<>();
        employeeRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Employee save(Employee obj){
        employeeRepository.save(obj);
        return obj;
    }

    @Override
    public void delete(Employee obj) {employeeRepository.delete(obj);}

    @Override
    public void deleteByID(Integer id){employeeRepository.deleteById(id);}

    @Override
    public Optional<Employee> findByID(Integer id){return employeeRepository.findById(id);}
}
