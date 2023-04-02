package com.cuspay.controller;

import com.cuspay.controller.main.Attributes;
import com.cuspay.model.Stats;
import com.cuspay.model.Users;
import com.cuspay.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reg")
public class RegCont extends Attributes {

    @GetMapping
    public String reg(Model model) {
        AddAttributes(model);
        return "reg";
    }

    @PostMapping
    public String regUser(Model model, @RequestParam String username, @RequestParam String password) {
        if (usersRepo.findAll().isEmpty() || usersRepo.findAll().size() == 0) {
            Users user = new Users(username, password, Role.ADMIN);
            user.addStat(new Stats());
            usersRepo.save(user);
            return "redirect:/login";
        }

        if (usersRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует!");
            AddAttributes(model);
            return "reg";
        }

        Users user = new Users(username, password, Role.CLIENT);
        user.addStat(new Stats());
        usersRepo.save(user);

        return "redirect:/login";
    }
}
