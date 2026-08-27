package com.khonghung.laptopshop.controller.admin;

import com.khonghung.laptopshop.domain.User;
import com.khonghung.laptopshop.service.UploadServices;
import com.khonghung.laptopshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadServices uploadServices;
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadServices uploadServices, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadServices = uploadServices;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/user")
    public String getUserTablePage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User users = this.userService.getAllUsersById(id).orElse(null);
        model.addAttribute("users", users);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }

    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model,
                                 @ModelAttribute("newUser") @Valid User hungdeptrai,
                                 BindingResult newUserBindingResult,
                                 @RequestParam("hungkhongFile") MultipartFile file) {

        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            System.out.println (error.getField() + " - " + error.getDefaultMessage());
        }

        if(newUserBindingResult.hasErrors()){
            return "admin/user/create";
        }

        String avatar = this.uploadServices.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(hungdeptrai.getPassword());
        hungdeptrai.setAvatar(avatar);
        hungdeptrai.setPassword(hashPassword);
        hungdeptrai.setRole(this.userService.getRoleByName(hungdeptrai.getRole().getName()));
        this.userService.handleSaveUser(hungdeptrai);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/{id}/edit")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User users = this.userService.getAllUsersById(id).orElse(null);
        model.addAttribute("id", id);
        model.addAttribute("updateUser", users);
        return "admin/user/update";
    }

    @PostMapping(value = "/admin/user/{id}/edit")
    public String updateUserPage(Model model,
                                 @ModelAttribute("updateUser") @Valid User updateUser,
                                 BindingResult updateUserBindingResult,
                                 @PathVariable long id,
                                 @RequestParam("hungkhongFile") MultipartFile file) {
        if (updateUserBindingResult.hasFieldErrors("fullName") || updateUserBindingResult.hasFieldErrors("phone")) {
            model.addAttribute("id", id);
            return "admin/user/update";
        }
        User currentUser = this.userService.getAllUsersById(id).orElse(null);
        if (currentUser != null) {
            currentUser.setFullName(updateUser.getFullName());
            currentUser.setPhone(updateUser.getPhone());
            currentUser.setAddress(updateUser.getAddress());
            if (!file.isEmpty()) {
                String oldAvatar = currentUser.getAvatar();
                String newAvatar = this.uploadServices.handleSaveUploadFile(file, "avatar");
                currentUser.setAvatar(newAvatar);
                this.uploadServices.handleDeleteFile(oldAvatar, "avatar");
            }
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/{id}/delete")
    public String deleteUserPage(Model model, @PathVariable long id) {
        User users = this.userService.getAllUsersById(id).orElse(null);
        model.addAttribute("id", id);
        return "admin/user/delete";
    }

    @GetMapping("/admin/user/{id}/delete/success")
    public String deleteUserSuccess(Model model, @PathVariable long id) {
        User user = this.userService.getAllUsersById(id).orElse(null);
        if (user != null) {
            this.uploadServices.handleDeleteFile(user.getAvatar(), "avatar");
            this.userService.deleteUserById(id);
        }
        return "redirect:/admin/user";
    }
}
