package com.khonghung.laptopshop.controller;

import com.khonghung.laptopshop.domain.User;
import com.khonghung.laptopshop.repository.UserRepository;
import com.khonghung.laptopshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    //Home page
    @RequestMapping("/")
    public String getHomePage(Model model) {
        return "redirect:/admin/user";
    }


    //Table user
    @RequestMapping("/admin/user")
    public String getUserTablePage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/table-user";
    }

    //User Detail
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User users = this.userService.getAllUsersById(id).orElse(null);
        model.addAttribute("users", users);
        model.addAttribute("id", id);
        return "admin/user/show";
    }

    //Create new user
    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User hungdeptrai) {
        this.userService.handleSaveUser(hungdeptrai);
        return "redirect:/admin/user";
    }

    //Update user
    @RequestMapping("/admin/user/{id}/edit")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User users = this.userService.getAllUsersById(id).orElse(null);
        model.addAttribute("id", id);
        model.addAttribute("updateUser", users);
        return "admin/user/update-user";
    }

    @RequestMapping(value = "/admin/user/{id}/edit", method = RequestMethod.POST)
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
    @RequestMapping("/admin/user/{id}/delete")
    public String deleteUserPage(Model model, @PathVariable long id) {
        User users = this.userService.getAllUsersById(id).orElse(null);
        model.addAttribute("id", id);
        return "admin/user/delete-user";
    }

    @RequestMapping("admin/user/{id}/delete/success")
    public String deleteUserSuccess(Model model, @PathVariable long id) {
        User users = this.userService.getAllUsersById(id).orElse(null);
        userService.deleteUserById(id);
        return "redirect:/admin/user";
    }




}
