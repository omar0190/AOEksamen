package com.ao.aoeksamenprojekt.controller;


import com.ao.aoeksamenprojekt.model.Employee;
import com.ao.aoeksamenprojekt.service.position.EmployeeServiceJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController  {


    private EmployeeServiceJPA employeeServiceJPA;

    public LoginController(EmployeeServiceJPA employeeServiceJPA) {
        this.employeeServiceJPA = employeeServiceJPA;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/signup")
    public String signUp(){
        return "signUp";
    }


   @PostMapping("signed")
   public String signed(Employee employee){

       // vi sætter nu objektet i databasen
       employeeServiceJPA.save(employee);
       System.out.println("Employee created");

        return "redirect:/signup";
   }
}
