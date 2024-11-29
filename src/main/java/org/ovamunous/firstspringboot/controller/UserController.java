package org.ovamunous.firstspringboot.controller;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.ovamunous.firstspringboot.model.User;
import org.ovamunous.firstspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String allUsers(ModelMap model) {
        System.out.println(userService.getUsers());
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        return "index";
    }

    @PostMapping("/action")
    public String action(@ModelAttribute("newUser") User newUser,
                         @RequestParam("action") String action, @RequestParam("id") String id,
                         ModelMap model) {
        switch (action) {
            case "add":
                userService.addUser(newUser);
                break;
            case "update":
                try {
                    User oldUser = userService.getUser(Integer.parseInt(id));
                    oldUser.setPassword(newUser.getPassword());
                    oldUser.setEmail(newUser.getEmail());
                    oldUser.setUsername(newUser.getUsername());
                    userService.updateUser(oldUser);
                } catch (Exception e) {
                    throw new EntityNotFoundException("User not found");
                }
                break;
            case "delete":
                try {
                    userService.deleteUser(userService.getUser(Integer.parseInt(id)));
                } catch (Exception e) {
                    throw new EntityNotFoundException("User not found");
                }
                break;
        }
        newUser = null;
        return "redirect:/";
    }
}