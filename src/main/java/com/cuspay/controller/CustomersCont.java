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
@RequestMapping("/customers")
public class CustomersCont extends Attributes {

    @GetMapping
    public String customers(Model model) {
        AddAttributesCustomers(model);
        return "customers";
    }

    @PostMapping("/add")
    public String userAdd(@RequestParam String name) {
        Users user = new Users(name, name, Role.CLIENT);
        user.addStat(new Stats());
        usersRepo.save(user);
        return "redirect:/customers";
    }
}
