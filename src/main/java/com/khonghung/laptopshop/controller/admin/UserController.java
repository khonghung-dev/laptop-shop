package com.khonghung.laptopshop.controller.admin;

import com.khonghung.laptopshop.domain.User;
import com.khonghung.laptopshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    //Home page
    @GetMapping("/")
    public String getHomePage(Model model) {
        return "redirect:/admin/user";
    }


    //Table user
    @GetMapping("/admin/user")
    public String getUserTablePage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    //User Detail
    @GetMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User users = this.userService.getAllUsersById(id).orElse(null);
        model.addAttribute("users", users);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }

    //Create new user
    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") User hungdeptrai) {
        this.userService.handleSaveUser(hungdeptrai);
        return "redirect:/admin/user";
    }

    //Update user
    @GetMapping("/admin/user/{id}/edit")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User users = this.userService.getAllUsersById(id).orElse(null);
        model.addAttribute("id", id);
        model.addAttribute("updateUser", users);
        return "admin/user/update";
    }

    @PostMapping(value = "/admin/user/{id}/edit")
    public String updateUserPage(Model model, @ModelAttribute("updateUser") User updateUser) {
        User currentUser = this.userService.getAllUsersById(updateUser.getId()).orElse(null);
        if(currentUser != null) {
            currentUser.setFullName(updateUser.getFullName());
            currentUser.setPhone(updateUser.getPhone());
            currentUser.setAddress(updateUser.getAddress());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    //Delete User
    @GetMapping("/admin/user/{id}/delete")
    public String deleteUserPage(Model model, @PathVariable long id) {
        User users = this.userService.getAllUsersById(id).orElse(null);
        model.addAttribute("id", id);
        return "admin/user/delete";
    }

    @GetMapping("/admin/user/{id}/delete/success")
    public String deleteUserSuccess(Model model, @PathVariable long id) {
        User users = this.userService.getAllUsersById(id).orElse(null);
        userService.deleteUserById(id);
        return "redirect:/admin/user";
    }
}
