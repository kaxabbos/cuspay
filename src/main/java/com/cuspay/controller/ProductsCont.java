package com.cuspay.controller;

import com.cuspay.controller.main.Attributes;
import com.cuspay.model.Fines;
import com.cuspay.model.Incomes;
import com.cuspay.model.Products;
import com.cuspay.model.Users;
import com.cuspay.model.enums.ProductStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsCont extends Attributes {

    @GetMapping
    public String products(Model model) {
        AddAttributesProducts(model);
        return "products";
    }

    @GetMapping("/my")
    public String productsMy(Model model) {
        AddAttributes(model);
        return "productsMy";
    }

    @GetMapping("/withdraw/{id}")
    public String productWithdraw(@PathVariable Long id) {
        Products product = productsRepo.getReferenceById(id);
        product.setFine(new Fines((int) (product.getPrice().getPrice() * product.getWeight().getWeight())));
        product.setStatus(ProductStatus.WITHDRAW);
        productsRepo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/pay/fine/{id}")
    public String productFine(@PathVariable Long id) {
        Products product = productsRepo.getReferenceById(id);
        product.getOwner().getStat().setFine(product.getOwner().getStat().getFine() + product.getFine().getFine());
        product.setStatus(ProductStatus.WAITING);
        Long fineId = product.getFine().getId();
        product.setFine(null);
        finesRepo.deleteById(fineId);
        productsRepo.save(product);
        return "redirect:/products/my";
    }

    @GetMapping("/delete/{id}")
    public String productDelete(@PathVariable Long id) {
        productsRepo.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/pay/{id}")
    public String productsPay(@PathVariable Long id) {
        Products product = productsRepo.getReferenceById(id);
        Incomes incomes = product.getOwner().getStat().getIncomes();
        incomes.setIncome(incomes.getIncome() + product.getPrice().getPrice());
        product.setStatus(ProductStatus.PAID_FOR);
        productsRepo.save(product);
        return "redirect:/products/my";
    }

    @PostMapping("/add")
    public String productAdd(@RequestParam Long userId, @RequestParam String name, @RequestParam float weight, @RequestParam int price) {
        Users user = usersRepo.getReferenceById(userId);
        user.addProduct(new Products(name, weight, price));
        usersRepo.save(user);
        return "redirect:/products";
    }
}
