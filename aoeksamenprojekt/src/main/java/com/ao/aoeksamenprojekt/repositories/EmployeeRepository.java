package com.ao.aoeksamenprojekt.repositories;

import com.ao.aoeksamenprojekt.model.Employee;
import com.ao.aoeksamenprojekt.service.CRUDService;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
