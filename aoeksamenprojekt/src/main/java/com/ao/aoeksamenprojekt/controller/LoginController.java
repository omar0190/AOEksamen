package com.ao.aoeksamenprojekt.controller;


import com.ao.aoeksamenprojekt.auth.User;
import com.ao.aoeksamenprojekt.auth.UserService;
import com.ao.aoeksamenprojekt.service.position.EmployeeServiceJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController  {


    private EmployeeServiceJPA employeeServiceJPA;
    private UserService userService;

    public LoginController(EmployeeServiceJPA employeeServiceJPA, UserService userService) {
        this.employeeServiceJPA = employeeServiceJPA;
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/signup")
    public String signUp(){
        return "signUp";
    }


   @PostMapping("/signed")
   public String signed(User user){

       // vi sætter nu objektet i databasen
       userService.create(user);
       System.out.println("Employee created");

        return "redirect:/login";
   }
}
