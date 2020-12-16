package com.ao.aoeksamenprojekt.controller;

import com.ao.aoeksamenprojekt.model.Employee;
import com.ao.aoeksamenprojekt.service.position.EmployeeServiceJPA;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class EmployeeController {
    EmployeeServiceJPA employeeServiceJPA;

    public EmployeeController(EmployeeServiceJPA employeeServiceJPA) {
        this.employeeServiceJPA = employeeServiceJPA;
    }

    @GetMapping("/ansatte")
    public String ansatte(Model model) {
        ArrayList<Employee> list = employeeServiceJPA.findAll();

        System.out.println(list.get(0).getFirstName());

        model.addAttribute("list", list);
        return "Employee/employees";

    }

    @GetMapping("/opretvikar")
    public String create() {


        return "Employee/create";
    }

    @PostMapping("/employeecreated")
    public String created(Employee employee) {

        employeeServiceJPA.save(employee);

        return "redirect:/ansatte";
    }

    @GetMapping("/sletemp{id}")
    public String delete(@PathVariable ("id") int id){

        employeeServiceJPA.deleteByID(id);

        return "redirect:/ansatte";
    }


    @GetMapping("/empdetails{id}")
    public String showEmployees(@PathVariable("id") int id, Model model) {
        Optional<Employee> employee1 = employeeServiceJPA.findByID(id);
        if (employee1.isPresent()) {
            model.addAttribute("id", employee1.get().getID());
            model.addAttribute("firstName", employee1.get().getFirstName());
            model.addAttribute("lastName", employee1.get().getLastName());
            model.addAttribute("email", employee1.get().getEmail());
            model.addAttribute("phoneNumber", employee1.get().getPhoneNumber());
        }

        return "Employee/edit";
    }

    @PostMapping("/editedemp")
    public String edited(@ModelAttribute Employee employee){

        employeeServiceJPA.save(employee);

        return "redirect:/ansatte";
    }


    @GetMapping("/kontaktmedarbejder")
    public String contact(Model model) {

        ArrayList<Employee> list = employeeServiceJPA.findAll();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getFirstName());
        }
        model.addAttribute("list", list);

        return "Employee/contact";
    }

    @GetMapping("/empprofile{id}")
    public String empprofile(@PathVariable("id") int id, Model model) {
        Optional<Employee> employee = employeeServiceJPA.findByID(id);
        if (employee.isPresent()) {
            model.addAttribute("id", employee.get().getID());
            model.addAttribute("first", employee.get().getFirstName());
            model.addAttribute("last", employee.get().getLastName());
            model.addAttribute("email", employee.get().getEmail());
            model.addAttribute("phone", employee.get().getPhoneNumber());

        }
        return "Employee/profile";
    }

}
