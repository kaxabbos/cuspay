package com.cuspay.controller;

import com.cuspay.controller.main.Attributes;
import com.cuspay.model.Users;
import com.cuspay.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersCont extends Attributes {
    @GetMapping
    public String users(Model model) {
        AddAttributesUsers(model);
        return "users";
    }

    @PostMapping("/edit/{id}")
    public String userEdit(@PathVariable Long id, @RequestParam Role role) {
        Users user = usersRepo.getReferenceById(id);
        user.setRole(role);
        usersRepo.save(user);
        return "redirect:/users";
    }

    @PostMapping("/edit/password")
    public String userEdit(@RequestParam String password) {
        Users user = getUser();
        user.updatePassword(password);
        usersRepo.save(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String userDelete(Model model, @PathVariable Long id) {
        Users user = usersRepo.getReferenceById(id);
        if (user == getUser()) {
            AddAttributesUsers(model);
            model.addAttribute("message", "Вы не можете удалить свой профиль!");
            return "users";
        }
        usersRepo.deleteById(id);
        return "redirect:/users";
    }
}
