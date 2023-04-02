package com.cuspay.controller.main;

import com.cuspay.model.Users;
import com.cuspay.model.enums.Role;
import org.springframework.ui.Model;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
        model.addAttribute("username", getUsername());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAll());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesStats(Model model) {
        AddAttributes(model);
        model.addAttribute("stats", statsRepo.findAll());
    }

    protected void AddAttributesStat(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("owner", usersRepo.getReferenceById(id));
    }

    protected void AddAttributesCustomers(Model model) {
        AddAttributes(model);
        model.addAttribute("customers", usersRepo.findAll());
    }

    protected void AddAttributesProducts(Model model) {
        AddAttributes(model);
        model.addAttribute("customers", usersRepo.findAll());
        model.addAttribute("products", productsRepo.findAll());
    }
}
