package com.healthturing.healthturing_server.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthturing.healthturing_server.dto.RegisterRequestDTO;
import com.healthturing.healthturing_server.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/admin/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UsersAdminRestController {

    @Autowired
    private UserService userService;




    @DeleteMapping("/{id}")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String deleteUser(@PathVariable long id){

        try {

            userService.deleteUser(id);
            
        } catch (Exception e) {
            
        }
        
        return "redirect:/admin/users";
    }

    @PostMapping("")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String createAdminUser(@Valid @RequestBody RegisterRequestDTO request) {
        try{
            userService.createAdmin(request.getEmail(), request.getName(), request.getPassword());
        }catch(Error e){

        }
        
        return "redirect:/admin/users";
    }
    



    
    
}
