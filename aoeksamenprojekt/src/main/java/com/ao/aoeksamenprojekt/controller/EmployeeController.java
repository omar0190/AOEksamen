package com.ao.aoeksamenprojekt.controller;

import com.ao.aoeksamenprojekt.model.Employee;
import com.ao.aoeksamenprojekt.service.position.EmployeeServiceJPA;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class EmployeeController {
    EmployeeServiceJPA employeeServiceJPA;

    public EmployeeController(EmployeeServiceJPA employeeServiceJPA) {this.employeeServiceJPA = employeeServiceJPA;}
    @GetMapping("/ansatte")
    public String ansatte(Model model){
        ArrayList<Employee> list = employeeServiceJPA.findAll();
        Employee employee1 = list.get(0);
        System.out.println(employee1.getSurName());
        System.out.println(employee1.getLastName());
        System.out.println(employee1.getAge());
        System.out.println(employee1.getEmail());
        System.out.println(employee1.getPhoneNumber());
        model.addAttribute("employees", list);
        return "employees";
    }
        @GetMapping("/empldetails{id}")
    public String showEmployees(@PathVariable("id") int id, Model model){
            Optional<Employee> employee1 = employeeServiceJPA.findByID(id);
            if (employee1.isPresent()){
                model.addAttribute("surName", employee1.get().getSurName());
                model.addAttribute("lastName", employee1.get().getLastName());
                model.addAttribute("age", employee1.get().getAge());
                model.addAttribute("email", employee1.get().getEmail());
                model.addAttribute("phoneNumber", employee1.get().getPhoneNumber());
            }
            return "vikarprofil";
        }

}
