package com.ao.aoeksamenprojekt.controller;

import com.ao.aoeksamenprojekt.model.Employee;
import com.ao.aoeksamenprojekt.model.Position;
import com.ao.aoeksamenprojekt.service.position.EmployeeServiceJPA;
import com.ao.aoeksamenprojekt.service.position.PositionServiceJPA;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class EmployeeController {
    EmployeeServiceJPA employeeServiceJPA;
    PositionServiceJPA positionServiceJPA;

    public EmployeeController(EmployeeServiceJPA employeeServiceJPA, PositionServiceJPA positionServiceJPA) {
        this.employeeServiceJPA = employeeServiceJPA;
        this.positionServiceJPA = positionServiceJPA;
    }

    @GetMapping("/vikar")
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

        return "redirect:/vikar";
    }

    @GetMapping("/sletemp{id}")
    public String delete(@PathVariable ("id") int id){

        employeeServiceJPA.deleteByID(id);

        return "redirect:/vikar";
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

        return "redirect:/vikar";
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

            // vi laver en if statement her, da koden giver exception når employees stilling er null
            if(employee.get().getPosition() != null){
                model.addAttribute("stilling", employee.get().getPosition().getTitle());
            }
        }
        return "Employee/profile";
    }

    @GetMapping("/tildel{id}")
    public String tildel(@PathVariable("id") int id, Model model){

        Optional<Employee> employee = employeeServiceJPA.findByID(id);


        if(employee.isPresent()){
            model.addAttribute("id", employee.get().getID());
            model.addAttribute("first", employee.get().getFirstName());
            model.addAttribute("last", employee.get().getLastName());
            if(employee.get().getPosition() != null){
                            model.addAttribute("stilling", employee.get().getPosition().getTitle());
            }

            model.addAttribute("stillinger", positionServiceJPA.findAll());

        }

        return "Employee/tildelJob";
    }


    @PostMapping("/tildelt")
    public String tildelt(@RequestParam int id, @RequestParam int stilling_id){


        // for at indsætte stillings data i emp tabel, så oprette vi 2 objekter, hvor vi henter ved hjælp fra id
        Optional<Employee> employee = employeeServiceJPA.findByID(id);
        Optional<Position> position = positionServiceJPA.findByID(stilling_id);


        if(employee.isPresent() && position.isPresent()){
            // her indsætter vi stillings objektet ind employee -> postion objektet, hvor den også gemmes i databasen
             employee.get().setPosition(position.get());
             // nu gemmer vi emp i databasen, så position også kommer med
            employeeServiceJPA.save(employee.get());
        }


        System.out.println("stilling er tildelt");

        return "redirect:/vikar";
    }



}
